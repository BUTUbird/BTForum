package org.butu.service;

import org.butu.model.entity.Tip;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 每日赠言 服务类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-01
 */
public interface TipService extends IService<Tip> {
    Tip getRandomTip();
}
