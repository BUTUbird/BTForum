//package org.butu.utils;
//
//import org.butu.model.entity.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//import javax.mail.internet.MimeMessage;
//
///**
// * @program: BTForum
// * @description: 邮箱工具类
// * @packagename: org.butu.utils
// * @author: BUTUbird
// * @date: 2022-03-24 23:17
// **/
//@Component
//public class MailServiceUtils {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    private JavaMailSender mailSender;
//
//    // 生成链接,并给接收的邮箱发送邮件
//    public void sendCode(User user){
//        MimeMessage message = mailSender.createMimeMessage();
//        try{
//            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
//            messageHelper.setFrom("发送方的邮箱地址"); //发送方的邮箱地址，而不是接收方的邮箱地址
//            messageHelper.setTo(user.getEmail()); // 接收方的邮箱地址
//            messageHelper.setSubject("注册");  // 邮箱标题
//            String html = "<html>\n" +
//                    "<body>\n" +
//                    "<p>请点击下方链接验证</p>\n" +
//                    "<a href=\"http://localhost:8080/user/mail/"+user.getId()+"\">http://localhost:8081/user/mail/"+user.getId()+"</a>" +
//                    "</body>\n" +
//                    "</html>";
//            messageHelper.setText(html,true); // 邮箱内容
//            mailSender.send(message);  // 发送邮箱
//            System.out.println("发送成功");
//        }catch (Exception e){
//            System.out.println("发送失败");
//        }
//    }
//
//}
