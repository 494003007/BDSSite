package com.bdssite.modules.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("performance")
public class PerformanceController {
    @RequestMapping("dashboard")
    public String dashBoard(){
        return "/performance/dashboard";
    }
}
