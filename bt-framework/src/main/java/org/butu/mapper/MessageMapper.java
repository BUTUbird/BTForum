package org.butu.mapper;

import org.butu.model.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author BUTUbird
 * @since 2022-04-15
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}
