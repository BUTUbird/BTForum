package org.butu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vdurmont.emoji.EmojiParser;
import org.butu.common.api.ApiResult;
import org.butu.config.security.util.WordFilter.WordFilter;
import org.butu.model.dto.PostDTO;
import org.butu.model.entity.*;
import org.butu.model.vo.PostVO;
import org.butu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;


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
    @Autowired
    private TagService tagService;
    @Autowired
    private PostTagService postTagService;
    @Autowired
    private CommentService commentService;


    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = postService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public ApiResult<Post> create(@RequestBody PostDTO dto, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        Post post = postService.create(dto, user);
        return ApiResult.success(post);
    }

    @GetMapping("/detailOne/{id}")
    public ApiResult<Map<String, Object>> view(@RequestParam("id") String id) {
        Map<String, Object> map = postService.viewPost(id);
        return ApiResult.success(map);
    }

    @GetMapping("/recommend")
    public ApiResult<List<Post>> getRecommend(@RequestParam("topicId") String id) {
        List<Post> posts = postService.getRecommend(id);
        return ApiResult.success(posts);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update")
    public ApiResult<Post> update(@Valid @RequestBody Post post, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        Assert.isTrue(user.getId().equals(post.getUserId()), "非本人无权修改");
        post.setModifyTime(new Date());
        post.setContent(EmojiParser.parseToAliases(WordFilter.replaceWords(post.getContent())));
        postService.updateById(post);
        return ApiResult.success(post);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/delete/{id}")
    public ApiResult<String> delete(@PathVariable("id") String id, Principal principal) {
        User umsUser = userService.getUserByUsername(principal.getName());
        Post byId = postService.getById(id);
        Assert.notNull(byId, "来晚一步，话题已不存在");
        Assert.isTrue(byId.getUserId().equals(umsUser.getId()), "你为什么可以删除别人的话题？？？");
        postService.removeById(id);
        return ApiResult.success(null, "删除成功");
    }

    /**
     * 帖子后台管理
     *
     * @param id
     * @return
     */
    @DeleteMapping("/deleteOne/{id}")
    public ApiResult<String> deleteOne(@PathVariable("id") String id) {
        Post post = postService.getById(id);
        User user = userService.getUserById(post.getUserId());
        //user.setTopiccount(user.getTopiccount() - 1);
        userService.updateById(user);
        List<PostTag> bmsTopicTags = postTagService.selectByTopicId(id);
        bmsTopicTags.forEach(topicTag -> {
            Tag tag = tagService.selectTagById(topicTag.getTagId());
            tag.setTopicCount(tag.getTopicCount() - 1);
            tagService.updateById(tag);
        });
        commentService.remove(new LambdaQueryWrapper<Comment>().eq(Comment::getTopicId, id));
        postService.removeById(id);
        return ApiResult.success(null, "删除成功");
    }

    @RequestMapping("/searchOne")
    public ApiResult<Page<PostVO>> searchOne(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                             @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                                             @RequestParam(value = "keyword") String keyword) {
        Page<PostVO> bmsPostPage = postService.searchByKey(keyword, new Page<>(pageNo, pageSize));
        return ApiResult.success(bmsPostPage);
    }
}

