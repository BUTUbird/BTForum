package org.butu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author BUTU
 */
@SpringBootApplication
@MapperScan("org.butu.mapper")
public class ForumApp {
    public static void main(String[] args) {
        SpringApplication.run(ForumApp.class, args);
    }
}
