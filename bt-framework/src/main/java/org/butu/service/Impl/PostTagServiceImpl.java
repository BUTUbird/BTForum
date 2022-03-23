package org.butu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.butu.mapper.PostTagMapper;
import org.butu.model.entity.PostTag;
import org.butu.model.entity.Tag;
import org.butu.service.PostTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service
@Transactional(rollbackFor = Exception.class)
public class PostTagServiceImpl extends ServiceImpl<PostTagMapper, PostTag> implements PostTagService {

    @Override
    public List<PostTag> selectByTopicId(String topicId) {
        QueryWrapper<PostTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PostTag::getTopicId, topicId);
        return this.baseMapper.selectList(wrapper);
    }
    @Override
    public void createPostTag(String id, List<Tag> tags) {
        // 先删除topicId对应的所有记录
        this.baseMapper.delete(new LambdaQueryWrapper<PostTag>().eq(PostTag::getTopicId, id));

        // 循环保存对应关联
        tags.forEach(tag -> {
            PostTag topicTag = new PostTag();
            topicTag.setTopicId(id);
            topicTag.setTagId(tag.getId());
            this.baseMapper.insert(topicTag);
        });
    }
    @Override
    public Set<String> selectTopicIdsByTagId(String id) {
        return this.baseMapper.getTopicIdsByTagId(id);
    }

}
