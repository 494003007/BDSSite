package com.recordme.modules.usermanage.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by D on 2017/2/23.
 */
public class HomePageController {
    @RequestMapping(value = "index")
    public String index(){
        return "index";
    }
}
