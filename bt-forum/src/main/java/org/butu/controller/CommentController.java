package org.butu.controller;


import org.butu.common.api.ApiResult;
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
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @GetMapping("/get_comments")
    public ApiResult<List<CommentVO>>getCommentsByByPostID(@RequestParam(value = "topicid",defaultValue = "1")String postId){
        List<CommentVO>comment = commentService.getCommentsByPostId(postId);
        return ApiResult.success(comment);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add_comment")
    public ApiResult<Comment> add_comment(@RequestBody CommentDTO dto, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        Comment comment = commentService.create(dto, user);
        return ApiResult.success(comment);
    }
}
