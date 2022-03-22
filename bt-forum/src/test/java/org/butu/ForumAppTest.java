package org.butu;


import org.butu.config.redis.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: BTForum
 * @description: 测试类
 * @packagename: org.butu
 * @author: BUTUbird
 * @date: 2022-03-15 23:45
 **/

@SpringBootTest
public class ForumAppTest {
    @Autowired
    private RedisCache redisCache;
    @Test
    public void test(){
        redisCache.deleteObject("User:1499001665137684481");

    }
}
