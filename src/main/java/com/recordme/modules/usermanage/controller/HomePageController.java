package com.recordme.modules.usermanage.controller;

import com.recordme.modules.common.Tool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by D on 2017/2/23.
 */
@Controller
public class HomePageController {
    @RequestMapping(value = "index")
    public String index(){
        Tool.getUser();
        return "index";
    }
}
