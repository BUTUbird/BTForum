package org.butu.controller;


import org.butu.common.api.ApiResult;
import org.butu.model.dto.CommentDTO;
import org.butu.model.entity.Comment;
import org.butu.model.entity.User;
import org.butu.model.vo.CommentVO;
import org.butu.service.CommentService;
import org.butu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.butu.utils.JwtUtil.USER_NAME;

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
    @PostMapping("/add_comment")
    public ApiResult<Comment> add_comment(@RequestHeader(value = USER_NAME) String userName,
                                          @RequestBody CommentDTO dto) {
        User user = userService.getUserByUsername(userName);
        Comment comment = commentService.create(dto, user);
        return ApiResult.success(comment);
    }
}
