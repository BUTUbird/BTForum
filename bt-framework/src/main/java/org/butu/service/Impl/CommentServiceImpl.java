package org.butu.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.butu.config.security.util.WordFilter.WordFilter;
import org.butu.model.dto.CommentDTO;
import org.butu.model.entity.Comment;
import org.butu.mapper.CommentMapper;
import org.butu.model.entity.User;
import org.butu.model.vo.CommentVO;
import org.butu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Slf4j
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public List<CommentVO> getCommentsByPostId(String postId) {
        List<CommentVO> comment = new ArrayList<CommentVO>();
        try {
            comment = this.baseMapper.getCommentsByPostID(postId);
        } catch (Exception e) {
            log.info("遍历Comment失败");
        }
        return comment;
    }

    @Override
    public Comment create(CommentDTO dto, User user) {
        Comment comment = Comment.builder()
                .userId(user.getId())
                .content(WordFilter.replaceWords(dto.getContent()))
                .topicId(dto.getTopic_id())
                .createTime(new Date())
                .build();
        this.baseMapper.insert(comment);
        return comment;
    }
}
