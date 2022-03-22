package org.butu.config.security.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.butu.model.entity.User;
import org.butu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: BTForum
 * @description: 密码校验
 * @packagename: org.butu.config.security.config
 * @author: BUTUbird
 * @date: 2022-03-15 11:24
 **/
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    @Lazy
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取登录用户信息
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        List<String> list = new ArrayList<>(Arrays.asList("test","admin"));
        if (user != null){
            return new UserDetailsImpl(user,list);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
