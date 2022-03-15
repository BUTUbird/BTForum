package org.butu.config.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @program: BTForum
 * @description: 动态权限相关业务类
 * @packagename: org.butu.config.security.component
 * @author: BUTUbird
 * @date: 2022-03-15 11:47
 **/
public interface DynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP
     * @return 资源集合
     */
    Map<String, ConfigAttribute> loadDataSource();
}
