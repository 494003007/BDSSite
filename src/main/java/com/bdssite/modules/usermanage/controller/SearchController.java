package com.bdssite.modules.usermanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by D on 2017/5/6.
 */
@Controller
@RequestMapping("Search")
public class SearchController {
    @RequestMapping("")
    public String searchPage(){
        return "search/search";
    }

}
