package org.butu.service;

import org.butu.model.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-04-15
 */
public interface MessageService extends IService<Message> {

    void createMessage(String parentId, String id, String content);

    void sendMessage(String id);
}
