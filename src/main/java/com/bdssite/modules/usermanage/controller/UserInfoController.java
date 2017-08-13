package com.bdssite.modules.usermanage.controller;

import com.bdssite.modules.usermanage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ed_cc on 2017/2/16.
 * Edit by D on 2017/3/25.
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

//    @RequestMapping("list")
//    public String userList(Model model){
//        model.addAttribute("userInfo",userService.findAll());
//        return "usermanage/userList";
//    }
//
//    @RequestMapping("view")
//    public String userView(Model model,Long uid){
//        if(uid != null){
//            model.addAttribute("userInfo",userService.findByUid(uid));
//        }else{
//            model.addAttribute("userInfo",new UserInfo());
//        }
//
//        return "usermanage/userView";
//    }
}
