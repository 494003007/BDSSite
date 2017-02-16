package com.recordme.modules.usermanage.controller;

import com.alibaba.fastjson.JSON;
import com.recordme.modules.usermanage.entity.UserInfo;
import com.recordme.modules.usermanage.services.UserService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;

/**
 * Created by D on 2017/2/12.
 */
@Controller
@RequestMapping("/")
public class AccountManageController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String registerPage(@ModelAttribute UserInfo user){

        return "/usermanage/register";
    }
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String register(UserInfo user){
        String salt = generateSalt();
        user.setSalt(salt);
        salt = user.getUsername() + salt;
        user.setPassword(new Md5Hash(user.getPassword(), salt, 2).toString());
        userService.save(user);
        System.out.println(user.toString());
        return "redirect:/usermanage/login";
    }


    @RequestMapping(value="login",method= RequestMethod.POST)
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception {
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");

        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理.
        return "/usermanage/login";
    }

    @RequestMapping(value="login",method= RequestMethod.GET)
    public String loginPage(){
        return "/usermanage/login";
    }

    private String generateSalt(){
        Random RANDOM = new SecureRandom();
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
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
