package com.recordme.modules.usermanage.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ed_cc on 2017/2/16.
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

    @RequiresPermissions("userInfo:add")
    public String userAdd(){
        return "usermanage/userAdd";
    }
}
