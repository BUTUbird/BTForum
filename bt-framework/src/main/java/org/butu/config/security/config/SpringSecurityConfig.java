package org.butu.config.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;

/**
 * @program: BTForum
 * @description: SpringSecurity配置文件
 * @packagename: org.butu.config.security.config
 * @author: BUTUbird
 * @date: 2022-03-15 00:21
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //白名单配置
        List<String> urls = ignoreUrlsConfig.getUrls();
        for (String url : urls){
            http.authorizeRequests()
                    .antMatchers(url)
                    .permitAll();
        }
    }
}
