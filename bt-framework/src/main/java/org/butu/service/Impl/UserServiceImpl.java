package org.butu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.butu.common.api.ApiResult;
import org.butu.common.exception.ApiAsserts;
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
import org.butu.utils.MD5Utils;
import org.butu.utils.JwtUtil;
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

    //    @Override
//    public String executeLogin(LoginDTO dto) {
//        String token = null;
//        try {
//            User user = getUserByUsername(dto.getUsername());
//            String encodePwd = MD5Utils.getPwd(dto.getPassword());
//            if (!encodePwd.equals(user.getPassword())){
//                throw new Exception("密码错误");
//            }
//            token = JwtUtil.generateToken(String.valueOf(user.getUsername()));
//        }catch (Exception e){
//            log.warn("用户不存在or密码验证失败=======>{}", dto.getUsername());
//
//        }
//        return token;
//    }

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
            token = jwtTokenUtil.generateToken(userDetails);
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
        BeanUtils.copyProperties(user, profile);
        //用户文章数
        int count = postMapper.selectCount(new LambdaQueryWrapper<Post>().eq(Post::getUserId, userId)).intValue();
        profile.setTopicCount(count);

        //粉丝数
        int followers = followMapper.selectCount((new LambdaQueryWrapper<Follow>().eq(Follow::getParentId, userId))).intValue();

        profile.setFollowerCount(followers);
        return profile;
    }

}
