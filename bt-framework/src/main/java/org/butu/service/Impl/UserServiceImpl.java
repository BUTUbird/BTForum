package org.butu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.butu.common.api.ApiResult;
import org.butu.config.RedisCache;
import org.butu.model.dto.LoginDTO;
import org.butu.model.dto.RegisterDTO;
import org.butu.model.entity.LoginUser;
import org.butu.model.entity.User;
import org.butu.mapper.UserMapper;
import org.butu.model.vo.LoginVO;
import org.butu.model.vo.UserInfoVo;
import org.butu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.butu.utils.BeanCopyUtils;
import org.butu.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;
import java.util.Objects;


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
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

//    @Override
//    public User executeRegister(RegisterDTO dto) {
//        //查询是否相同的用户名
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getUsername, dto.getName())
//                .or().eq(User::getEmail, dto.getEmail());
//        User user = baseMapper.selectOne(queryWrapper);
//        if (!ObjectUtils.isEmpty(user)) {
//            ApiResult.failed("账号或邮箱已存在！");
//        }
//        User addUser = User.builder()
//                .username(dto.getName())
//                .alias(dto.getName())
//                //TODO 此处需要MD5加密
//                .password(MD5Utils.getPwd(dto.getPass()))
//                .email(dto.getEmail())
//                .createTime(new Date())
//                .status(true)
//                .build();
//        baseMapper.insert(addUser);
//        return addUser;
//    }

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

//    @Override
//    public User getUserByUsername(String username) {
//        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,username));
//    }

    @Override
    public ApiResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("User:"+userId, loginUser);
        //把token和userid封装 返回
        //把user转换成userInfo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        LoginVO vo = new LoginVO(jwt,userInfoVo);

        return ApiResult.success(vo);
    }


}
