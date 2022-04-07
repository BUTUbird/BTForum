package org.butu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.butu.common.api.ApiResult;
import org.butu.model.dto.ChildCommentDTO;
import org.butu.model.vo.PageVo;
import org.butu.service.UserService;
import org.butu.utils.BeanCopyUtils;
import org.butu.utils.WordFilter.WordFilter;
import org.butu.model.dto.CommentDTO;
import org.butu.model.entity.Comment;
import org.butu.mapper.CommentMapper;
import org.butu.model.entity.User;
import org.butu.model.vo.CommentVO;
import org.butu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private WordFilter wordFilter;
    @Autowired
    private UserService userService;

    @Override
    public List<CommentVO> getCommentsByPostId(String postId,Integer pageNum, Integer pageSize) {
        //查询对应帖子的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //对postId进行查询
        queryWrapper.eq(Comment::getTopicId, postId);
        //根评论 rootId为-1
        queryWrapper.eq(Comment::getRootId, -1);
        //分页
        Page<Comment> page = new Page(pageNum, pageSize);
        page(page,queryWrapper);
        List<CommentVO> commentVOList = toCommentVoList(page.getRecords());
        //查询所有根评论对应的子评论集合，并赋给对应的属性
        for(CommentVO commentVO: commentVOList){
            //查询对应根评论的子评论
            List<CommentVO> childComment = getChildren(commentVO.getId());
            //赋值
            commentVO.setChildren(childComment);
        }
        return commentVOList;
    }

    @Override
    public Comment create(CommentDTO dto, User user) {
        Comment comment = Comment.builder()
                .userId(user.getId())
                .content(wordFilter.replaceWords(dto.getContent()))
                .topicId(dto.getTopic_id())
                .createTime(new Date())
                .build();
        this.baseMapper.insert(comment);
        return comment;
    }

    @Override
    public Comment create(ChildCommentDTO dto, User user) {

        Comment comment = Comment.builder()
                .userId(user.getId())
                .content(wordFilter.replaceWords(dto.getChild_comment_content()))
                .topicId(this.getById(dto.getContent_parent_id()).getTopicId())
                .rootId(dto.getContent_parent_id())
                .createTime(new Date())
                .build();
        this.baseMapper.insert(comment);
        return comment;
    }

    private List<CommentVO> toCommentVoList(List<Comment> list){
        List<CommentVO> commentVos = BeanCopyUtils.copyBeanList(list,CommentVO.class);
        //遍历vo集合
        for(CommentVO commentVo: commentVos){
            //通过createBy 查询用户的昵称并赋值
            String userName = userService.getById(commentVo.getUserId()).getUsername();
            commentVo.setUsername(userName);
            //通过toCommentUserId查询用户的昵称并赋值

            String avatar = userService.getById(commentVo.getUserId()).getAvatar();
            commentVo.setAvatar(avatar);

            Integer roleId = userService.getById(commentVo.getUserId()).getRoleId();
            commentVo.setRoleId(roleId);
            //如果toCommentUserId不为-1才进行查询
            if (commentVo.getParentId() != null){
                String parentUserName = userService.getById(commentVo.getUserId()).getUsername();
                commentVo.setParentUserName(parentUserName);
            }
        }
        return commentVos;
    }

    /**
     * 根据根评论的id查询所对应的字评论的集合
     * @param id id根评论的id
     * @return
     */
    private List<CommentVO> getChildren(String id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment>comments = list(queryWrapper);

        List<CommentVO> commentVos = toCommentVoList(comments);
        return commentVos;
    }
}
