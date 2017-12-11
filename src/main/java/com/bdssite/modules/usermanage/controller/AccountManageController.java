package com.bdssite.modules.usermanage.controller;


import com.bdssite.modules.usermanage.entity.ShortMessage;
import com.bdssite.modules.usermanage.entity.UserInfo;
import com.bdssite.modules.usermanage.services.RoleService;
import com.bdssite.modules.usermanage.services.ShortMessageService;
import com.bdssite.modules.usermanage.services.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.BASE64Encoder;


import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Created by D on 2017/2/12.
 */
@Controller
@RequestMapping("/")
public class AccountManageController {

    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @Autowired
    private UserService userService;
    @Autowired
    private ShortMessageService shortMessageService;

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
        user =userService.findByUsername(user.getUsername());
        UserInfo admin =userService.findByUsername("admin");
        ShortMessage shortMessage = new ShortMessage();
        shortMessage.setContent("欢迎来到 [易职搜]!");
        shortMessage.setFromUser(admin);
        shortMessage.setToUser(user);
        shortMessageService.save(shortMessage);
        System.out.println(user.toString());
        return "redirect:/login";
    }


        @RequestMapping(value="loginI",method= RequestMethod.POST)
    public String login(String username, String password, Boolean rememberMe, HttpServletRequest request, Map<String, Object> map, HttpSession httpSession) throws Exception {
        Subject subject = SecurityUtils.getSubject();

        String msg = "";

        UserInfo userInfo = userService.findByUsername(username);
        if(userInfo.getState() == (byte)2){
            msg = "accountLockedError";
        }else{
            UsernamePasswordToken upt;
            if(rememberMe != null && rememberMe){
                upt = new UsernamePasswordToken(username,password,rememberMe);
            }else{
                upt = new UsernamePasswordToken(username,password);
            }
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
    public String retrievePassword( String email,HttpServletRequest request, Map<String, Object> map){
        System.out.println("**********");
        //##################################################################################################//
        UserInfo user = userService.findByEmail(email);
        String salt = generateSalt();
        user.setSalt(salt);
        salt = user.getUsername() + salt;
        String newPassword = generateRetrievePassword();
        String savePassword = new Md5Hash(newPassword, salt, 2).toString();
        user.setPassword(savePassword);
        userService.save(user);
        //##################################################################################################//
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setFrom(new InternetAddress("18923955429@163.com", "易职搜", "UTF-8"));
            mimeMessageHelper.setSubject("[易职搜]请重置您的密码!");
            StringBuilder str = new StringBuilder("<html><head></head><body><h1>");
            str.append("Hello!<br/>");
            str.append("您的新密码为:<br/>"+newPassword);
            str.append("</h1></body></html>");
            // true 表示启用html
            mimeMessageHelper.setText(str.toString(),true);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 发送邮件
        javaMailSender.send(mimeMessage);

        System.out.println("邮件已发送");
        return "usermanage/retrieveSuccess";
    }

    private String generateRetrievePassword(){
        String secretKey = UUID.randomUUID().toString();
        return new BASE64Encoder().encode(secretKey.getBytes()).substring(0, ((int) (Math.random()*6)+8));
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
