package org.butu.mapper;

import org.butu.model.entity.Tip;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 每日赠言 Mapper 接口
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-01
 */
@Mapper
public interface TipMapper extends BaseMapper<Tip> {
    Tip getRandomTip();
}
