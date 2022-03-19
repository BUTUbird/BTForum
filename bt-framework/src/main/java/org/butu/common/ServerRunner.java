package org.butu.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @program: BTForum
 * @description: 运行提示
 * @packagename: org.butu.common
 * @author: BUTUbird
 * @date: 2022-03-16 20:22
 **/
@Component
public class ServerRunner implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(ServerRunner.class);
    @Override
    public void run(String... args) throws Exception {
        // 打开浏览器
        log.info("Knife4j：http://127.0.0.1:8000/doc.html#/home ⚡");
    }
}
