package org.butu.service.Impl;

import org.butu.model.entity.Billboard;
import org.butu.mapper.BillboardMapper;
import org.butu.service.BillboardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 全站公告 服务实现类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-01
 */
@Service
public class BillboardServiceImpl extends ServiceImpl<BillboardMapper, Billboard> implements BillboardService {

    @Override
    public void insertOne(String content) {
        Billboard billboard = Billboard.builder().content(content).createTime(new Date()).shows(true).build();
        this.baseMapper.insert(billboard);
    }
}
