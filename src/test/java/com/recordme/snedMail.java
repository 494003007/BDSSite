package com.recordme;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Ed_cc on 2017/5/23.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class snedMail {
    @Test
    public void begin() throws MessagingException {
//        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
//        senderImpl.setHost("smtp.163.com");
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo("c494003007@qq.com");// bnuzeasyjob bnuzeasyjob0
//        mailMessage.setFrom("18923955429@163.com");//m18923955429@163.com注册成功！
//        mailMessage.setSubject(" 测试简单文本邮件发送！ ");
//        mailMessage.setText(" 测试我的简单邮件发送机制！！ ");
//        senderImpl.setUsername("18923955429@163.com"); // 根据自己的情况,设置username
//        senderImpl.setPassword("bnuzeasyjob0"); // 根据自己的情况, 设置password
//        Properties prop = new Properties();
//        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
//        prop.put("mail.smtp.timeout", "25000");
//        senderImpl.setJavaMailProperties(prop);
//        // 发送邮件
//        senderImpl.send(mailMessage);
//
//        System.out.println(" 邮件发送成功.. ");

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        // 设置邮件服务器
        javaMailSender.setHost("smtp.163.com");
        javaMailSender.setUsername("18923955429@163.com");
        javaMailSender.setPassword("bnuzeasyjob0");
        javaMailSender.setDefaultEncoding("UTF-8");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo("c494003007@qq.com");
        mimeMessageHelper.setFrom("18923955429@163.com");
        mimeMessageHelper.setSubject("测试java邮件-html邮件的发送");
        StringBuilder str = new StringBuilder("<html><head></head><body><h1>");
        str.append("hello!this is spring mail test。<br/>");
        str.append("spring 邮件测试。<br/>");
        str.append("</h1></body></html>");
        // true 表示启用html
        mimeMessageHelper.setText(str.toString(),true);

        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");  // 验证用户名和密码
        properties.put("mail.smtp.timeout","25000");

        javaMailSender.setJavaMailProperties(properties);
        // 发送邮件
        javaMailSender.send(mimeMessage);

        System.out.println("邮件已发送");
    }
}
