package org.butu.service;

import org.butu.model.dto.CommentDTO;
import org.butu.model.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.butu.model.entity.User;
import org.butu.model.vo.CommentVO;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
public interface CommentService extends IService<Comment> {

    List<CommentVO> getCommentsByPostId(String postId);

    Comment create(CommentDTO dto, User user);
}
