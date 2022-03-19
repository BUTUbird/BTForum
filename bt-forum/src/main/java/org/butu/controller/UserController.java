package org.butu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.butu.common.api .ApiResult;
import org.butu.config.redis.RedisService;
import org.butu.model.dto.LoginDTO;
import org.butu.model.dto.RegisterDTO;
import org.butu.model.entity.Comment;
import org.butu.model.entity.Post;
import org.butu.model.entity.User;
import org.butu.service.CommentService;
import org.butu.service.PostService;
import org.butu.service.UploadService;
import org.butu.service.UserService;
import org.butu.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private RedisService redisService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UploadService uploadService;


    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public ApiResult<Map<String, Object>>register(@Valid @RequestBody RegisterDTO dto){
        String code = (String) redisService.get("KAPTCHA_KEY");
        if (!code.equals(dto.getCode())){
            return ApiResult.failed("验证码错误");
        }
        User user = userService.executeRegister(dto);
        if (ObjectUtils.isEmpty(user)){
            return ApiResult.failed("账号注册失败");
        }
        Map<String, Object>map = new HashMap<>(16);
        map.put("user",user);
        return ApiResult.success(map);
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ApiResult<Map<String, String>>login(@Valid @RequestBody LoginDTO dto){
        String code = (String) redisService.get("KAPTCHA_KEY");
        if (!code.equals(dto.getCode())){
            return ApiResult.failed("验证码错误");
        }
        String token = userService.executeLogin(dto);
        if (ObjectUtils.isEmpty(token)){
            return ApiResult.failed("账号密码错误");
        }
        Map<String, String>map = new HashMap<>(16);
        map.put("token",token);
        return ApiResult.success(map,"登录成功");
    }


    @ApiOperation(value = "获取用户信息")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/info")
    public ApiResult<User> getUser(Principal principal){
        User user = userService.getUserByUsername(principal.getName());
        return ApiResult.success(user);
    }

    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    public ApiResult<Object>logout(){
        return ApiResult.success(null,"注销成功");
    }

    @ApiOperation(value = "获取用户名")
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
    @ApiOperation(value = "更新用户信息")
    @PostMapping("/update")
    public ApiResult<User> updateUser(@RequestBody User umsUser) {
        userService.updateById(umsUser);
        return ApiResult.success(umsUser);
    }

    @ApiOperation(value = "上传头像")
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/uploadImg")
    public ApiResult uploadImg(MultipartFile file,Principal principal){
        String url = uploadService.upload(file);
        userService.updateImg(url,principal.getName());
        return ApiResult.success(url);
    }

    @ApiOperation(value = "验证码")
    @GetMapping("/verify")
    public void createImageCode(HttpServletResponse response) throws IOException{
        //禁止缓存
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        //设置响应格式为png图片
        response.setContentType("image/png");
        // 生成图片验证码
        BufferedImage image = new BufferedImage(300, 75, BufferedImage.TYPE_INT_RGB);
        String randomText = VerifyCodeUtils.drawRandomText(image);
        System.out.println("验证码->>>"+randomText);
        redisService.set("KAPTCHA_KEY", randomText, 60);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "png", out);
        out.flush();
        out.close();
    }

    /**
     * 后台用户管理
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "获取所有用户")
    @RequestMapping("/getAll")
    public ApiResult<Page<User>> getAll(@RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                           @RequestParam(value = "size", defaultValue = "10") Integer pageSize)
    {
        Page<User> umsUserPage=userService.page(new Page<>(pageNo, pageSize));
        return ApiResult.success(umsUserPage);
    }
    @ApiOperation(value = "删除用户")
    @DeleteMapping("/deleteOne/{id}")
    public ApiResult<String> deleteOne(@PathVariable("id") String id)
    {
        userService.removeById(id);
        postService.remove(new LambdaQueryWrapper<Post>().eq(Post::getUserId, id));
        commentService.remove(new LambdaQueryWrapper<Comment>().eq(Comment::getUserId,id));
        return ApiResult.success(null,"删除成功");
    }

    @ApiOperation(value = "查找用户")
    @RequestMapping("/searchOne")
    public ApiResult<Page<User>> searchOne(@RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                              @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                                              @RequestParam(value = "key") String key)
    {
        Page<User> umsUserPage = userService.searchKey(key,new Page<>(pageNo, pageSize));
        return ApiResult.success(umsUserPage);
    }



    @ApiOperation(value = "获取token")
    @PostMapping("/token")
    public ApiResult<Map<String, String>>token(@Valid @RequestBody LoginDTO dto){
        String token = userService.executeLogin(dto);
        if (ObjectUtils.isEmpty(token)){
            return ApiResult.failed("账号密码错误");
        }
        Map<String, String>map = new HashMap<>(16);
        map.put("token",token);
        return ApiResult.success(map,"登录成功");
    }
}
