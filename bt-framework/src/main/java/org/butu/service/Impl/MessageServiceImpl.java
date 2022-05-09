package org.butu.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.butu.config.WebSocketServer;
import org.butu.model.entity.Message;
import org.butu.mapper.MessageMapper;
import org.butu.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.butu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-04-15
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    @Autowired
    private UserService userService;

    @Override
    public void createMessage(String parentId, String id, String content) {
        Message message = Message.builder()
                .toUserId(id)
                .fromUserId(parentId)
                .createTime(LocalDateTime.now())
                .readStatus("0")
                .fromUserName(userService.getById(parentId).getUsername())
                .content(content)
                .build();
        this.baseMapper.insert(message);
    }

    @Override
    public void sendMessage(String userId) {
        LambdaQueryWrapper<Message> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Message::getToUserId, userId);
        wrapper.eq(Message::getReadStatus, "0");
        List<Message> messages = baseMapper.selectList(wrapper);
        WebSocketServer.sendInfo(JSON.toJSONString(messages), userId);
    }
}
