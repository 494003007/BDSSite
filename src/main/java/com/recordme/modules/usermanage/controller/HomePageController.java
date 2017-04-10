package com.recordme.modules.usermanage.controller;

import com.recordme.modules.common.Tool;

import com.recordme.modules.usermanage.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by D on 2017/2/23.
 */
@Controller
public class HomePageController {
    @RequestMapping(value = "index")
    public String index(Model model){
        UserInfo userInfo = Tool.getUser();
        if(userInfo != null){
            model.addAttribute("userInfo",userInfo);
        }

        return "index";
    }
}
