package org.butu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.butu.model.entity.PostTag;
import org.butu.model.entity.Tag;

import java.util.List;
import java.util.Set;

public interface PostTagService extends IService<PostTag> {

    /**
     * 获取Topic Tag 关联记录
     *
     * @param topicId TopicId
     * @return
     */
    List<PostTag> selectByTopicId(String topicId);
    /**
     * 创建中间关系
     *
     * @param id
     * @param tags
     * @return
     */
    void createPostTag(String id, List<Tag> tags);
    /**
     * 获取标签换脸话题ID集合
     *
     * @param id
     * @return
     */
    Set<String> selectTopicIdsByTagId(String id);

}
