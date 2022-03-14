package org.butu.config.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: BTForum
 * @description: 白名单资源路径配置
 * @packagename: org.butu.config.security.config
 * @author: BUTUbird
 * @date: 2022-03-14 23:21
 **/
@Data
@ConfigurationProperties(prefix = "secure.ignored")
@Component
public class IgnoreUrlsConfig {
    /**
     * 白名单
     */
    private List<String> urls = new ArrayList<>();
}
