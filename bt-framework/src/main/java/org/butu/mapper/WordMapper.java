package org.butu.mapper;

import org.butu.model.entity.Word;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 敏感词库 Mapper 接口
 * </p>
 *
 * @author BUTUbird
 * @since 2022-04-02
 */
@Mapper
public interface WordMapper extends BaseMapper<Word> {

}
