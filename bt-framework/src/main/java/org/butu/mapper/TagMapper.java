package org.butu.mapper;

import org.butu.model.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}
