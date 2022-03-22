package org.butu.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.butu.config.redis.RedisCache;
import org.butu.model.entity.Tip;
import org.butu.mapper.TipMapper;
import org.butu.service.TipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

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
    private RedisCache redisCache;

    @Override
    public Tip getRandomTip() {
        Tip todayTip = null;
        try {
            //查找本地redis有无数据
            todayTip = (Tip) redisCache.getCacheObject("today_tip");
            //没有数据
            if (ObjectUtils.isEmpty(todayTip)) {
                //从数据库里获取数据
                todayTip = this.baseMapper.getRandomTip();
                //将数据存在redis，并设置24小事刷新一次
                redisCache.setCacheObject("today_tip", todayTip, 24 * 60 * 60, TimeUnit.SECONDS);
            }

        }catch (Exception e) {
            log.info("tip转化失败");
        }
        return todayTip;
    }
}
