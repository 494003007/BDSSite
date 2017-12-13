package com.bdssite.modules.usermanage.controller;

import com.bdssite.tool.CommonTool;

import com.bdssite.modules.usermanage.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by D on 2017/2/23.
 */
@Controller
public class HomePageController {
    @RequestMapping(value = {"index","/"})
    public String index(Model model,HttpSession httpSession){
        UserInfo userInfo = CommonTool.getUser(httpSession);
        if(userInfo != null){
            model.addAttribute("userInfo",userInfo);
        }

        return "index";
    }

}
