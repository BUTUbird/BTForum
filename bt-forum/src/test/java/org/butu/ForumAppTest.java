package org.butu;



import com.alibaba.fastjson.JSON;
import org.butu.config.redis.RedisCache;
import org.butu.config.security.util.JwtTokenUtil;
import org.butu.mapper.MenuMapper;
import org.butu.model.entity.Tip;
import org.butu.model.entity.User;
import org.butu.utils.MailServiceUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;


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
    @Autowired
    private MailServiceUtils mailServiceUtils;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Test
    public void test(){


//        String today_tip = JSON.toJSONString(redisCache.getCacheObject("today_tip"));
//        Tip tip = JSON.parseObject(redisCache.getCacheObject("today_tip"), Tip.class);
//        Object today_tip = redisCache.getCacheObject("today_tip");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Tip tip = objectMapper.convertValue(today_tip, Tip.class);
//        System.out.println(tip.getContent());
//        Tip tip = Tip.builder()
//                .author("test")
//                .content("test")
//                .id(1)
//                .build();
//        String s = JSON.toJSONString(tip);
//        System.out.println(s);
//        redisCache.setCacheObject("today_tip", tip, 60*60, TimeUnit.SECONDS);
        User user = User.builder()
                .username("Mail")
                .email("1781894948@qq.com")
                .id("1")
                .build();
//        mailServiceUtils.sendCode(user);
        String userNameFromToken = jwtTokenUtil.getUserNameFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNYWlsIiwiY3JlYXRlZCI6MTY0ODMwNjc2NTA3NCwiZXhwIjoxNjQ4OTExNTY1fQ.u1zuio8xUxu1N_jsI5Iwvu12Lmm9hzDi_5IljqcfQcJTH4AZzykHXoqbPVTdZmU0NqP_iRrEPS2R5OcJzLN7ew");
        System.out.println(userNameFromToken);
    }
}
