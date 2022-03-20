package org.butu.service.Impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.butu.common.api.ApiResult;
import org.butu.common.exception.ApiAsserts;
import org.butu.config.redis.RedisService;
import org.butu.config.security.util.JwtTokenUtil;
import org.butu.mapper.FollowMapper;
import org.butu.mapper.PostMapper;
import org.butu.model.dto.LoginDTO;
import org.butu.model.dto.RegisterDTO;
import org.butu.model.entity.Follow;
import org.butu.model.entity.Post;
import org.butu.model.entity.User;
import org.butu.mapper.UserMapper;
import org.butu.model.vo.ProfileVO;
import org.butu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisService redisService;
    @Override
    public User executeRegister(RegisterDTO dto) {
        //查询是否相同的用户名
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, dto.getName())
                .or().eq(User::getEmail, dto.getEmail());
        User user = baseMapper.selectOne(queryWrapper);
        if (!ObjectUtils.isEmpty(user)) {
            ApiResult.failed("账号或邮箱已存在！");
        }
        User addUser = User.builder()
                .username(dto.getName())
                .alias(dto.getName())
                //TODO 此处需要MD5加密
//                .password(MD5Utils.getPwd(dto.getPass()))
                //使用SpringSecurity进行加密
                .password(passwordEncoder.encode(dto.getPass()))
                .email(dto.getEmail())
                .createTime(new Date())
                .status(true)
                .build();
        baseMapper.insert(addUser);
        return addUser;
    }

    @Override
    public String executeLogin(LoginDTO dto) {
        String token = null;
        try{
            UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
            if (!userDetails.isEnabled()){
                ApiAsserts.fail("账号已被锁定，请联系管理员处理");
            }
            boolean matches = passwordEncoder.matches(dto.getPassword(), userDetails.getPassword());
            if (!matches){
                throw new BadCredentialsException("密码错误");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            //修改在线状态
            LambdaUpdateWrapper<User> update = new LambdaUpdateWrapper<>();
            update.eq(User::getUsername, dto.getUsername());
            update.set(User::getLive, 1);
            baseMapper.update(null, update);

            token = jwtTokenUtil.generateToken(userDetails);

            redisService.set(userDetails.getUsername(), token, 60 * 60 * 24);
        }catch (UsernameNotFoundException e){
            log.warn("用户不存在=======>{}", dto.getUsername());
        }catch (BadCredentialsException e){
            log.warn("密码验证失败=======>{}", dto.getPassword());
        }
        return token;
    }

    @Override
    public User getUserByUsername(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    public ProfileVO getUserProfile(String userId) {

        ProfileVO profile = new ProfileVO();
        User user = baseMapper.selectById(userId);
        if (user == null ){
            ApiAsserts.fail("作者消失了，看看别的帖子吧！");
        }
        BeanUtils.copyProperties(user, profile);
        //用户文章数
        int count = postMapper.selectCount(new LambdaQueryWrapper<Post>().eq(Post::getUserId, userId)).intValue();
        profile.setTopicCount(count);

        //粉丝数
        int followers = followMapper.selectCount((new LambdaQueryWrapper<Follow>().eq(Follow::getParentId, userId))).intValue();

        profile.setFollowerCount(followers);
        return profile;
    }

    @Override
    public User getUserById(String id) {
        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId,id));
    }

    @Override
    public Page<User> searchKey(String key, Page<User> page) {
        Page<User> ipage = this.baseMapper.searchkey(page, key);
        return ipage;
    }

    @Override
    public void updateImg(String url,String username) {
        User user = new User();
        user.setAvatar(url);
        baseMapper.update(user,new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    public void logout(String name) {
        //修改在线状态
        LambdaUpdateWrapper<User> update = new LambdaUpdateWrapper<>();
        update.eq(User::getUsername, name);
        update.set(User::getLive, 0);
        baseMapper.update(null, update);

        //修改redis
        redisService.del(name);
    }

}
