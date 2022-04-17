package org.butu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @program: BTForum
 * @description: WebSocket配置类
 * @packagename: org.butu.config
 * @author: BUTUbird
 * @date: 2022-04-14 22:52
 **/
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}

