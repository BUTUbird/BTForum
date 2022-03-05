package org.butu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.butu.model.entity.Tag;
import org.butu.mapper.TagMapper;
import org.butu.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
