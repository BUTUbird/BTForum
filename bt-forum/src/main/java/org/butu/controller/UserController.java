package org.butu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.butu.common.api .ApiResult;
import org.butu.model.dto.LoginDTO;
import org.butu.model.dto.RegisterDTO;
import org.butu.model.entity.Post;
import org.butu.model.entity.User;
import org.butu.service.PostService;
import org.butu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;
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
    @Autowired
    private PostService postService;

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
        return ApiResult.success(map,"登录成功");
    }
    @GetMapping("/info")
    public ApiResult<User> getUser(@RequestHeader(value = USER_NAME ,required = false) String userName){
        User user = userService.getUserByUsername(userName);
        return ApiResult.success(user);
    }

    @GetMapping("/logout")
    public ApiResult<Object>logout(){
        return ApiResult.success(null,"注销成功");
    }

    @GetMapping("/{username}")
    public ApiResult<Map<String, Object>> getUserByName(@PathVariable("username") String username,
                                                        @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, Object> map = new HashMap<>(16);
        User user = userService.getUserByUsername(username);
        Assert.notNull(user, "用户不存在");
        Page<Post> page = postService.page(new Page<>(pageNo, size),
                new LambdaQueryWrapper<Post>().eq(Post::getUserId, user.getId()));
        map.put("user", user);
        map.put("topics", page);
        return ApiResult.success(map);
    }
    @PostMapping("/update")
    public ApiResult<User> updateUser(@RequestBody User umsUser) {
        userService.updateById(umsUser);
        return ApiResult.success(umsUser);
    }
}
