package org.butu.utils;

import org.butu.config.redis.RedisCache;
import org.butu.config.security.util.JwtTokenUtil;
import org.butu.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.TimeUnit;

/**
 * @program: BTForum
 * @description: 邮箱工具类
 * @packagename: org.butu.utils
 * @author: BUTUbird
 * @date: 2022-03-24 23:17
 **/
@Component
public class MailServiceUtils {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${spring.mail.username}")
    private String username;
    // 生成链接,并给接收的邮箱发送邮件
    public void sendCode(User user){
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            String token = jwtTokenUtil.generateToken(user.getUsername());
            redisCache.setCacheObject("mail", token, 60 * 30, TimeUnit.MINUTES);
            messageHelper.setFrom(username); //发送方的邮箱地址，而不是接收方的邮箱地址
            messageHelper.setTo(user.getEmail()); // 接收方的邮箱地址
            messageHelper.setSubject("注册");  // 邮箱标题
            String html = "<html>\n" +
                    "<body>\n" +
                    "<p>请点击下方链接进行密码重置</p>\n" +
                    "<a href=\"http://localhost:8080/#/resetPwd/"+token+"\">http://localhost:8080/#/resetPwd/"+token+"</a>" +
                    "</body>\n" +
                    "</html>";
            messageHelper.setText(html,true); // 邮箱内容
            mailSender.send(message);  // 发送邮箱
            System.out.println("发送成功");
        }catch (Exception e){
            System.out.println("发送失败");
        }
    }
}
