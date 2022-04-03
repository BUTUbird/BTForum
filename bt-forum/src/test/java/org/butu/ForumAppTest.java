package org.butu;



import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.butu.config.redis.RedisCache;
import org.butu.config.security.util.JwtTokenUtil;
import org.butu.mapper.MenuMapper;
import org.butu.mapper.WordMapper;
import org.butu.model.entity.Tip;
import org.butu.model.entity.User;
import org.butu.model.entity.Word;
import org.butu.service.WordService;
import org.butu.utils.MailServiceUtils;
import org.butu.utils.WordFilter.WordFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;
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
    @Autowired
    private WordService wordService;
    @Autowired
    private WordMapper wordMapper;
    @Autowired
    private WordFilter wordFilter;
    @Test
    public void test() throws IOException {
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
//        User user = User.builder()
//                .username("Mail")
//                .email("1781894948@qq.com")
//                .id("1")
//                .build();
////        mailServiceUtils.sendCode(user);
//        String userNameFromToken = jwtTokenUtil.getUserNameFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNYWlsIiwiY3JlYXRlZCI6MTY0ODMwNjc2NTA3NCwiZXhwIjoxNjQ4OTExNTY1fQ.u1zuio8xUxu1N_jsI5Iwvu12Lmm9hzDi_5IljqcfQcJTH4AZzykHXoqbPVTdZmU0NqP_iRrEPS2R5OcJzLN7ew");
//        System.out.println(userNameFromToken);
        // path + "/src/main/resources/words.txt"
        append("test1");

    }
    public static void append(String content){
        BufferedWriter out = null;
        try{
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(System.getProperty("user.dir")+ "\\src\\main\\resources\\words.txt",true)));
            out.write(content+"\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert out != null;
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test2() {
//        String words = wordFilter.replaceWords("二货和傻逼");
//        System.out.println(words);
        //System.out.println(new WordFilter().readWordsFromDataBase());
        List<String> list = wordFilter.readWordsFile("http://r8uj7z8yq.hd-bkt.clouddn.com/2022/04/02/4bbe4c1984554f6eac0f38753061467b.txt");

        for (String s : list) {
            Word word = Word.builder()
                    .word(s)
                    .build();
            LambdaQueryWrapper<Word> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Word::getWord, s);
            Word word1 = wordMapper.selectOne(queryWrapper);
            if (word1 == null) {
                wordMapper.insert(word);
            }else {
                System.out.println(word.getWord() + "已存在");
            }
        }
    }
}
