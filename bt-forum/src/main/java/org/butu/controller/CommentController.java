package org.butu.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.butu.common.api.ApiResult;
import org.butu.common.exception.ApiAsserts;
import org.butu.model.dto.ChildCommentDTO;
import org.butu.model.dto.CommentDTO;
import org.butu.model.entity.Comment;
import org.butu.model.entity.User;
import org.butu.model.vo.CommentVO;
import org.butu.service.CommentService;
import org.butu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Api(tags = "评论管理")
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取评论")
    @GetMapping("/get_comments")
    public ApiResult getCommentsByByPostID(@RequestParam(value = "topicid",defaultValue = "1") String postId) {

        List<CommentVO> comments = commentService.getCommentsByPostId(postId,1, 10);
        return ApiResult.success(comments);
    }

    @ApiOperation(value = "新增评论")
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add_comment")
    public ApiResult<Comment> add_comment(@RequestBody CommentDTO dto, Principal principal) {
        if (dto.getContent() == null || "".equals(dto.getContent()) || "\n".equals(dto.getContent())) {
            ApiAsserts.fail("评论内容不能为空");
        }
        User user = userService.getUserByUsername(principal.getName());
        Comment comment = commentService.create(dto, user);

        return ApiResult.success(comment);
    }
    @ApiOperation(value = "删除评论")
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/delete_comment/{id}")
    public ApiResult<String> delete_comment(@PathVariable("id") String id, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        Comment comment = commentService.getById(id);
        if (user.getId().equals(comment.getUserId())) {
            commentService.removeById(id);
        }else {
            ApiAsserts.fail("您无法删除不是您的评论哦");
        }
        return ApiResult.success("删除成功");
    }

    @ApiOperation(value = "新增二级评论")
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add_child_comment")
    public ApiResult<Comment> add_child_comment(@RequestBody ChildCommentDTO dto,Principal principal) {
        if (dto.getChild_comment_content() == null || "".equals(dto.getChild_comment_content()) || "\n".equals(dto.getChild_comment_content())) {
            ApiAsserts.fail("评论内容不能为空");
        }
        User user = userService.getUserByUsername(principal.getName());
        Comment comment = commentService.create(dto, user);
        return ApiResult.success(comment);
    }
}
