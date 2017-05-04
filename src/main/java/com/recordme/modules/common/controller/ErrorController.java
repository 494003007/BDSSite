package com.recordme.modules.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by D on 2017/4/25.
 */
@Controller
@RequestMapping("/error")
public class ErrorController {
    @RequestMapping("/noPermission")
    public String noPermission(){
        return "error/noPermission";
    }
}
