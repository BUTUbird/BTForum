package org.butu.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.butu.model.entity.PostTag;

import java.util.Set;


/**
 * @author BUTU
 */
@Mapper
public interface PostTagMapper extends BaseMapper<PostTag> {
    /**
     * 根据标签获取话题ID集合
     *
     * @param id
     * @return
     */
    Set<String> getTopicIdsByTagId(@Param("id") String id);
}
