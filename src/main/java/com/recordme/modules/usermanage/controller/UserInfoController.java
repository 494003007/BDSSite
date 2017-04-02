package com.recordme.modules.usermanage.controller;

import com.recordme.modules.usermanage.entity.UserInfo;
import com.recordme.modules.usermanage.services.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by Ed_cc on 2017/2/16.
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    UserService userService;
//
//    @RequiresPermissions("userInfo:add")
//    public String userAdd(){
//        return "usermanage/userAdd";
//    }

    @RequestMapping("list")
    public String userList(Model model){
        model.addAttribute("userInfo",userService.findAll());
        return "usermanage/userList";
    }

    @RequestMapping("view")
    public String userView(Model model,Long uid){
        if(uid != null){
            model.addAttribute("userInfo",userService.findByUid(uid));
        }else{
            model.addAttribute("userInfo",new UserInfo());
        }

        return "usermanage/userView";
    }
}
