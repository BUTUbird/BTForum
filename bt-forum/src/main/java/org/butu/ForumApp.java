package org.butu;

import org.butu.common.filter.JwtAuthenticationFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * @author BUTU
 */
@SpringBootApplication
@MapperScan("org.butu.mapper")
public class ForumApp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ForumApp.class, args);
    }
}
