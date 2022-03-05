package org.butu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.butu.model.vo.ProfileVO;
import org.butu.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.butu.service.PostTagService;
import org.butu.service.TagService;
import org.butu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.*;
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
    @Autowired
    private UserService userService;

    @Override
    public Page<PostVO> getList(Page<PostVO> page, String tab) {
        // 查询话题
        Page<PostVO> iPage = this.baseMapper.selectListAndPage(page, tab);
        // 查询话题的标签
        setPostTags(iPage);
        return iPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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

    @Override
    public Map<String, Object> viewPost(String id) {
        Map<String,Object> map = new HashMap<>(16);
        Post post = this.baseMapper.selectById(id);
        Assert.notNull(post,"当前话题不存在,或已被作者删除");
        //查询话题详情
        post.setView(post.getView()+1);
        this.baseMapper.updateById(post);
        //emoji转码
        post.setContent(EmojiParser.parseToUnicode(post.getContent()));
        map.put("post", post);
        //标签
        QueryWrapper<PostTag>wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PostTag::getTopicId, post.getId());
        Set<String> set = new HashSet<>();
        for (PostTag articleTag : postTagService.list(wrapper)){
            set.add(articleTag.getTagId());
        }
        List<Tag> tags = tagService.listByIds(set);
        map.put("tags", tags);
        //作者
        ProfileVO user = userService.getUserProfile(post.getUserId());
        map.put("user", user);

        return map;
    }

    @Override
    public List<Post> getRecommend(String id) {
        return this.baseMapper.selectRecommend(id);
    }

    @Override
    public Page<PostVO> searchByKey(String keyword, Page<PostVO> page) {
        // 查询话题
        Page<PostVO> iPage = this.baseMapper.searchByKey(page, keyword);
        // 查询话题的标签
        setPostTags(iPage);
        return iPage;
    }

    private void setPostTags(Page<PostVO> iPage) {
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
