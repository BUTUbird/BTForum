package org.butu.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.butu.model.entity.Tip;
import org.butu.mapper.TipMapper;
import org.butu.service.TipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 每日赠言 服务实现类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-01
 */
@Slf4j
@Service
public class TipServiceImpl extends ServiceImpl<TipMapper, Tip> implements TipService {

    @Override
    public Tip getRandomTip() {
        Tip todayTip = null;
        try {
            todayTip = this.baseMapper.getRandomTip();
        }catch (Exception e) {
            log.info("tip转化失败");
        }
        return todayTip;
    }
}
