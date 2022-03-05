package org.butu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vdurmont.emoji.EmojiParser;
import org.butu.mapper.TagMapper;
import org.butu.mapper.UserMapper;
import org.butu.model.dto.PostDTO;
import org.butu.model.entity.Post;
import org.butu.mapper.PostMapper;
import org.butu.model.entity.PostTag;
import org.butu.model.entity.Tag;
import org.butu.model.entity.User;
import org.butu.model.vo.PostVO;
import org.butu.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.butu.service.PostTagService;
import org.butu.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 话题表 服务实现类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Autowired
    private PostTagService postTagService;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TagService tagService;
    @Override
    public Page<PostVO> getList(Page<PostVO> page, String tab) {
        // 查询话题
        Page<PostVO> iPage = this.baseMapper.selectListAndPage(page, tab);
        // 查询话题的标签
        setTopicTags(iPage);
        return iPage;
    }

    @Override
    public Post create(PostDTO dto, User user) {
        Post post1 = this.baseMapper.selectOne(new LambdaQueryWrapper<Post>().eq(Post::getTitle, dto.getTitle()));
        Assert.isNull(post1, "话题已存在，请修改");
        //封装
        Post post = Post.builder()
                .userId(user.getId())
                .title(dto.getTitle())
                .content(EmojiParser.parseToAliases(dto.getContent()))
                .createTime(new Date())
                .build();
        this.baseMapper.insert(post);

        //用户积分增加
        int newScore = user.getScore() + 1;
        userMapper.updateById(user.setScore(newScore));

        //标签
        if (!ObjectUtils.isEmpty(dto.getTags())){
            //保存标签
            List<Tag> tags = tagService.insertTags(dto.getTags());
            //处理标签与话题的关联
            postTagService.createPostTag(post.getId(),tags);
        }
        return post;
    }

    private void setTopicTags(Page<PostVO> iPage) {
        iPage.getRecords().forEach(postTag -> {
            List<PostTag> postTags = postTagService.selectByTopicId(postTag.getId());
            if (!postTags.isEmpty()) {
                List<String> tagIds = postTags.stream().map(PostTag::getTagId).collect(Collectors.toList());
                List<Tag> tags = tagMapper.selectBatchIds(tagIds);
                postTag.setTags(tags);
            }
        });
    }
}
