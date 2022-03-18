package org.butu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.butu.model.entity.Post;
import org.butu.model.entity.Tag;
import org.butu.mapper.TagMapper;
import org.butu.model.vo.TagVO;
import org.butu.service.PostService;
import org.butu.service.PostTagService;
import org.butu.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Autowired
    @Lazy
    private PostService postService;

    @Autowired
    private PostTagService postTagService;
    @Override
    public List<Tag> insertTags(List<String> tags) {
        List<Tag> tagList = new ArrayList<>();
        for (String tagName : tags){
            Tag tag = this.baseMapper.selectOne(new LambdaQueryWrapper<Tag>().eq(Tag::getName, tagName));
            if (tag == null){
                tag = Tag.builder()
                        .name(tagName)
                        .build();
                this.baseMapper.insert(tag);
            }else {
                tag.setTopicCount(tag.getTopicCount()+1);
                this.baseMapper.updateById(tag);
            }
            tagList.add(tag);
        }

        return tagList;
    }

    @Override
    public Page<Post> selectPostByTagId(Page<Post> topicPage, String id) {
        // 获取关联的话题ID
        Set<String> ids = postTagService.selectTopicIdsByTagId(id);
        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Post::getId, ids);

        return postService.page(topicPage, wrapper);
    }

    @Override
    public Tag selectTagById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<TagVO> getTag() {
        return this.baseMapper.getTag();
    }
}
