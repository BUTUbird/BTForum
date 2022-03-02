package org.butu.service.Impl;

import org.butu.model.entity.Follow;
import org.butu.mapper.FollowMapper;
import org.butu.service.FollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户关注 服务实现类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

}
