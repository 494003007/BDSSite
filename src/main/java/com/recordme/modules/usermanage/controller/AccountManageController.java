package com.recordme.modules.usermanage.controller;


import com.recordme.modules.usermanage.entity.UserInfo;
import com.recordme.modules.usermanage.services.RoleService;
import com.recordme.modules.usermanage.services.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.BASE64Encoder;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
/**
 * Created by D on 2017/2/12.
 */
@Controller
@RequestMapping("/")
public class AccountManageController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String registerPage(Model model, @ModelAttribute UserInfo user){
        model.addAttribute("userInfo",new UserInfo());
        return "/usermanage/register";
    }
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String register(UserInfo user){
        user.setInfo_shield(0);
        user.setState((byte)1);
        String salt = generateSalt();
        user.setSalt(salt);
        salt = user.getUsername() + salt;
        user.setPassword(new Md5Hash(user.getPassword(), salt, 2).toString());
        userService.save(user);
        System.out.println(user.toString());
        return "redirect:/login";
    }


        @RequestMapping(value="loginI",method= RequestMethod.POST)
    public String login(String username, String password, Boolean rememberMe, HttpServletRequest request, Map<String, Object> map, HttpSession httpSession) throws Exception {
        Subject subject = SecurityUtils.getSubject();


        UsernamePasswordToken upt;
        if(rememberMe != null && rememberMe){
            upt = new UsernamePasswordToken(username,password,rememberMe);
        }else{
            upt = new UsernamePasswordToken(username,password);
        }

        String msg = "";

        try{
            subject.login(upt);
            httpSession.setAttribute(username, userService.findByUsername(username));
            return "redirect:index";
        }catch (IncorrectCredentialsException e){
            System.out.println("IncorrectCredentialsException -- > 密码不正确：");
            msg = "accountOrPwdError";
        }catch (UnknownAccountException e){
            System.out.println("UnknownAccountException -- > 账号不存在：");
            msg = "accountOrPwdError";
        }catch (Exception e){
            msg = "unknowError";
            System.out.println("else -- >" + e);
        }



        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理.
        return "usermanage/login";
    }

    @RequestMapping(value="login",method= RequestMethod.GET)
    public String loginPage(){
        return "usermanage/login";
    }

    @RequestMapping(value = "retrieve",method = RequestMethod.GET)
    public String retrievePasswordPage(){
        return "usermanage/retrieve";
    }

    @RequestMapping(value = "retrieve",method = RequestMethod.POST)
    public String retrievePassword( HttpServletRequest request, Map<String, Object> map){
        System.out.println("**********");
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        senderImpl.setHost("smtp.163.com");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("c494003007@qq.com");// bnuzeasyjob bnuzeasyjob0
        mailMessage.setFrom("18923955429@163.com");//m18923955429@163.com注册成功！
        mailMessage.setSubject(" 测试简单文本邮件发送！ ");
        mailMessage.setText(" 测试我的简单邮件发送机制！！ ");
        senderImpl.setUsername("18923955429@163.com"); // 根据自己的情况,设置username
        senderImpl.setPassword("bnuzeasyjob0"); // 根据自己的情况, 设置password
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", "25000");
        senderImpl.setJavaMailProperties(prop);
        // 发送邮件
        senderImpl.send(mailMessage);

        System.out.println(" 邮件发送成功.. ");
        return "usermanage/retrieve";
    }

    private String generateSalt(){
        Random random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String str = null;
        try {
            str = new String(salt,"UTF-8");
            str = new BASE64Encoder().encode(salt);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return str;
    }
}
