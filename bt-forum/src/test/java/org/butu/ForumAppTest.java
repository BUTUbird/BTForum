package org.butu;


import org.butu.config.redis.RedisCache;
import org.butu.mapper.MenuMapper;
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
    @Autowired
    private MenuMapper menuMapper;
    @Test
    public void test(){
        System.out.println(menuMapper.selectPermsByUserId("1504657025806737409"));

    }
}
