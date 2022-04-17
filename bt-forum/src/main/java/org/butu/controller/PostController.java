package org.butu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vdurmont.emoji.EmojiParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.butu.common.api.ApiResult;
import org.butu.model.vo.ViewVO;
import org.butu.utils.WordFilter.WordFilter;
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
@Api(tags = "帖子管理")
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

    @Autowired
    private WordFilter wordFilter;
    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = postService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }

    @ApiOperation(value = "关注列表")
    @GetMapping("/like")
    public ApiResult<Page<PostVO>> like(@RequestParam(value = "id") String id,
                                        @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = postService.getLike(new Page<>(pageNo, pageSize), id);
        return ApiResult.success(list);
    }


    @ApiOperation(value = "新增")
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public ApiResult<Post> create(@RequestBody PostDTO dto, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        Post post = postService.create(dto, user);
        return ApiResult.success(post);
    }

    @ApiOperation(value = "详情")
    @GetMapping()
    public ApiResult<Map<String, Object>> view(@RequestParam("id") String id) {
        Map<String, Object> map = postService.viewPost(id);
        return ApiResult.success(map);
    }

//    @ApiOperation(value = "随便看看")
//    @GetMapping("/recommend")
//    public ApiResult<List<Post>> getRecommend(@RequestParam("topicId") String id) {
//        List<Post> posts = postService.getRecommend(id);
//        return ApiResult.success(posts);
//    }
    @GetMapping("/hot")
    public ApiResult<List<ViewVO>> host(){
        List<ViewVO> posts = postService.getHotList();
        return ApiResult.success(posts);
    }

    @ApiOperation(value = "修改")
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update")
    public ApiResult<Post> update(@Valid @RequestBody Post post, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        Assert.isTrue(user.getId().equals(post.getUserId()), "非本人无权修改");
        post.setModifyTime(new Date());
        post.setContent(EmojiParser.parseToAliases(wordFilter.replaceWords(post.getContent())));
        postService.updateById(post);
        return ApiResult.success(post);
    }

    @ApiOperation(value = "删除")
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/delete/{id}")
    public ApiResult<String> delete(@PathVariable("id") String id, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        Post byId = postService.getById(id);
        Assert.notNull(byId, "来晚一步，话题已不存在");
        Assert.isTrue(byId.getUserId().equals(user.getId()), "你为什么可以删除别人的话题？？？");
        postService.removeById(id);
        return ApiResult.success(null, "删除成功");
    }

    /**
     * 帖子后台管理
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin')")
    @ApiOperation(value = "删除（管理员）")
    @DeleteMapping("/deleteOne/{id}")
    public ApiResult<String> deleteOne(@PathVariable("id") String id) {
        Post post = postService.getById(id);
        User user = userService.getUserById(post.getUserId());
        //user.setTopiccount(user.getTopiccount() - 1);
        userService.updateById(user);
        List<PostTag> postTags = postTagService.selectByTopicId(id);
        postTags.forEach(topicTag -> {
            Tag tag = tagService.selectTagById(topicTag.getTagId());
            tag.setTopicCount(tag.getTopicCount() - 1);
            tagService.updateById(tag);
        });
        commentService.remove(new LambdaQueryWrapper<Comment>().eq(Comment::getTopicId, id));
        postService.removeById(id);
        return ApiResult.success(null, "删除成功");
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @ApiOperation(value = "查找")
    @RequestMapping("/searchOne")
    public ApiResult<Page<PostVO>> searchOne(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                             @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                                             @RequestParam(value = "keyword") String keyword) {
        Page<PostVO> postPage = postService.searchByKey(keyword, new Page<>(pageNo, pageSize));
        return ApiResult.success(postPage);
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @ApiOperation(value = "列表（管理员）")
    @RequestMapping("/getAll")
    public ApiResult<Page<PostVO>> getAll(@RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                          @RequestParam(value = "size", defaultValue = "10") Integer pageSize)
    {
        Page<PostVO> postVOPage=postService.getList(new Page<>(pageNo, pageSize),"latest");
        return ApiResult.success(postVOPage);
    }
}

