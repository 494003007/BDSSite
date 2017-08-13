package com.bdssite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;


import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by Ed_cc on 2017/5/23.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class sendMail {
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Test
    public void begin() throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo("c494003007@qq.com");
        try {
            mimeMessageHelper.setFrom(new InternetAddress("18923955429@163.com", "易职搜", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mimeMessageHelper.setSubject("测试java邮件-html邮件的发送");
        StringBuilder str = new StringBuilder("<html><head></head><body><h1>");
        str.append("hello!this is spring mail test。<br/>");
        str.append("spring 邮件测试。<br/>");
        str.append("</h1></body></html>");
        // true 表示启用html
        mimeMessageHelper.setText(str.toString(),true);

        Properties properties = new Properties();
//        properties.put("mail.smtp.auth","true");  // 验证用户名和密码
//        properties.put("mail.smtp.timeout","25000");

//        javaMailSender.setJavaMailProperties(properties);
        // 发送邮件
        javaMailSender.send(mimeMessage);

        System.out.println("邮件已发送");

//
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//        try {
//            mimeMessageHelper.setTo("hzd138@qq.com");
//            try {
//                mimeMessageHelper.setFrom(new InternetAddress("18923955429@163.com", "易职搜", "UTF-8"));
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            mimeMessageHelper.setSubject("测试java邮件-html邮件的发送");
//            StringBuilder str = new StringBuilder("<html><head></head><body><h1>");
//            str.append("hello!this is spring mail test。<br/>");
//            str.append("spring 邮件测试。<br/>");
//            str.append("</h1></body></html>");
//            // true 表示启用html
//            mimeMessageHelper.setText(str.toString(), true);
//
//            Properties properties = new Properties();
//            properties.put("mail.smtp.auth", "true");  // 验证用户名和密码
//            properties.put("mail.smtp.timeout", "25000");
//
//            javaMailSender.setJavaMailProperties(properties);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//
//        mailSender.send(message);//发送邮件


    }

    public static void main(String[] args) throws MessagingException {

            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            // 设置邮件服务器
            javaMailSender.setHost("smtp.163.com");
            javaMailSender.setUsername("18923955429@163.com");
            javaMailSender.setPassword("bnuzeasyjob0");
            javaMailSender.setDefaultEncoding("UTF-8");
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo("c494003007@qq.com");
            try {
                mimeMessageHelper.setFrom(new InternetAddress("18923955429@163.com", "易职搜", "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
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
