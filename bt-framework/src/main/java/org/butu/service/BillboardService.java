package org.butu.service;

import org.butu.model.entity.Billboard;
import com.baomidou.mybatisplus.extension.service.IService;
import org.butu.model.vo.CountVO;

/**
 * <p>
 * 全站公告 服务类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-01
 */
public interface BillboardService extends IService<Billboard> {

    void insertOne(String content);

    CountVO statistics();
}
