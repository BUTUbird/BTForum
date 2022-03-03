package org.butu.controller;


import org.butu.common.api.ApiResult;
import org.butu.model.dto.LoginDTO;
import org.butu.model.dto.RegisterDTO;
import org.butu.model.entity.User;
import org.butu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static org.butu.utils.JwtUtil.USER_NAME;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ApiResult<Map<String, Object>>register(@Valid @RequestBody RegisterDTO dto){
        User user = userService.executeRegister(dto);
        if (ObjectUtils.isEmpty(user)){
            return ApiResult.failed("账号注册失败");
        }
        Map<String, Object>map = new HashMap<>(16);
        map.put("user",user);
        return ApiResult.success(map);
    }
    @PostMapping("/login")
    public ApiResult<Map<String, String>>login(@Valid @RequestBody LoginDTO dto){
        String token = userService.executeLogin(dto);
        if (ObjectUtils.isEmpty(token)){
            return ApiResult.failed("账号密码错误");
        }
        Map<String, String>map = new HashMap<>(16);
        map.put("token",token);
        return ApiResult.success(map);
    }
    @GetMapping("/info")
    public ApiResult<User> getUser(@RequestHeader(value = USER_NAME) String userName){
        User user = userService.getUserByUsername(userName);
        return ApiResult.success(user);
    }
    @GetMapping("/logout")
    public ApiResult<Object>logout(){
        return ApiResult.success(null,"注销成功");
    }
}
