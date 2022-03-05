package org.butu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vdurmont.emoji.EmojiParser;
import org.butu.common.api.ApiResult;
import org.butu.model.dto.PostDTO;
import org.butu.model.entity.Post;
import org.butu.model.entity.User;
import org.butu.model.vo.PostVO;
import org.butu.service.PostService;
import org.butu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.butu.utils.JwtUtil.USER_NAME;

/**
 * <p>
 * 话题表 前端控制器
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = postService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }
    @PostMapping("/create")
    public ApiResult<Post>create(@RequestHeader(value = USER_NAME,required = false) String userName, @RequestBody PostDTO dto){
        User user = userService.getUserByUsername(userName);
        Post post = postService.create(dto,user);
        return ApiResult.success(post);
    }
    @GetMapping()
    public ApiResult<Map<String, Object>>view(@RequestParam("id") String id){
        Map<String, Object> map = postService.viewPost(id);
        return ApiResult.success(map);
    }
    @GetMapping("/recommend")
    public ApiResult<List<Post>>getRecommend(@RequestParam("topicId")String id){
        List<Post> posts = postService.getRecommend(id);
        return ApiResult.success(posts);
    }
    @PostMapping("/update")
    public ApiResult<Post> update(@RequestHeader(value = USER_NAME) String userName, @Valid @RequestBody Post post) {
        User user = userService.getUserByUsername(userName);
        Assert.isTrue(user.getId().equals(post.getUserId()), "非本人无权修改");
        post.setModifyTime(new Date());
        post.setContent(EmojiParser.parseToAliases(post.getContent()));
        postService.updateById(post);
        return ApiResult.success(post);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<String> delete(@RequestHeader(value = USER_NAME) String userName, @PathVariable("id") String id) {
        User umsUser = userService.getUserByUsername(userName);
        Post byId = postService.getById(id);
        Assert.notNull(byId, "来晚一步，话题已不存在");
        Assert.isTrue(byId.getUserId().equals(umsUser.getId()), "你为什么可以删除别人的话题？？？");
        postService.removeById(id);
        return ApiResult.success(null,"删除成功");
    }
}

