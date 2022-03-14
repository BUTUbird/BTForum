package org.butu.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.butu.config.redis.RedisService;
import org.butu.model.entity.Tip;
import org.butu.mapper.TipMapper;
import org.butu.service.TipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
    @Autowired
    private RedisService redisService;

    @Override
    public Tip getRandomTip() {
        Tip todayTip = null;
        try {
            todayTip = (Tip) redisService.get("today_tip");
            if (ObjectUtils.isEmpty(todayTip)) {
                todayTip = this.baseMapper.getRandomTip();
                redisService.set("today_tip", todayTip, 24 * 60 * 60);
            }

        }catch (Exception e) {
            log.info("tip转化失败");
        }
        return todayTip;
    }
}
