package com.bdssite.modules.echart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Joey_Tsai on 2017/12/11.
 */
@Controller
@RequestMapping("echart")
public class EchartController {
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public String echartPage(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception{

        return "/echartPage/cityMapPage";

    }
    @RequestMapping(value = "photo",method = RequestMethod.GET)
    public String photo(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception{

        return "/echartPage/photo";

    }
}
