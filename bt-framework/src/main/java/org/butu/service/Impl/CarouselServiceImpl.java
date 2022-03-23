package org.butu.service.Impl;

import org.butu.model.dto.CarouselDTO;
import org.butu.model.entity.Carousel;
import org.butu.mapper.CarouselMapper;
import org.butu.service.CarouselService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 轮播图 服务实现类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-23
 */
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {

    @Override
    public void insertOne(CarouselDTO dto) {
        Carousel carousel = Carousel.builder()
                .name(dto.getName())
                .url(dto.getUrl())
                .createTime(new Date())
                .build();
        this.baseMapper.insert(carousel);
    }
}
