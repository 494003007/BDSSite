package com.recordme.modules.usermanage.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by D on 2017/5/6.
 */
@RequestMapping("Search")
public class SearchController {
    public String searchPage(){
        return "search/search";
    }
}
