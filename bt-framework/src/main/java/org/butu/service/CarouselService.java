package org.butu.service;

import org.butu.model.dto.CarouselDTO;
import org.butu.model.entity.Carousel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 轮播图 服务类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-23
 */
public interface CarouselService extends IService<Carousel> {

    void insertOne(CarouselDTO dto);
}
