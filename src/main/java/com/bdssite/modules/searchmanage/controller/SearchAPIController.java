package com.bdssite.modules.searchmanage.controller;



import com.bdssite.modules.common.RequestStatus;
import com.bdssite.modules.common.dto.ListDto;
import com.bdssite.modules.searchmanage.SolrManager;
import com.bdssite.modules.searchmanage.entity.Major;
import com.bdssite.modules.searchmanage.entity.Subject;
import com.bdssite.modules.searchmanage.service.MajorService;
import com.bdssite.modules.searchmanage.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


import java.net.URLEncoder;
import java.util.Map;


/**
 * Created by D on 2017/5/6.
 */
@Controller
@RequestMapping("searchApi")
public class SearchAPIController {
    @Autowired
    MajorService ms;

    @Autowired
    SubjectService ss;

    @Autowired
    SolrManager solrManager;

    @RequestMapping(value = "subject/byMajor")
    @ResponseBody
    public  ListDto<Subject> getMajorById(Long majorId){
        if(majorId!=null){
            return new ListDto<>(RequestStatus.SUCCESS, ss.findByMajor(ms.findOne(majorId)));
        }else{
            return new ListDto<>(RequestStatus.SUCCESS, null);
        }
    }

    @RequestMapping(value = "majors",method = RequestMethod.POST)
    @ResponseBody
    public ListDto<Major> getMajor(Long parentId){
        if(parentId == null){
            return new ListDto<>(RequestStatus.SUCCESS,ms.findParentMajor());
        }else{
            Major parentMajor = ms.findOne(parentId);
            return new ListDto<>(RequestStatus.SUCCESS,ms.findByParentMajor(parentMajor));
        }
    }

    private String searchUrlAppend(Map<String,Object> param) throws Exception{
        String baseSearchUrl = "findJob?wt=json";
        StringBuilder sb = new StringBuilder(baseSearchUrl);

        if(param.containsKey("keywords")){
           String keys = (String) param.get("keywords");
           if(keys.contains(",")){
               keys = keys.replaceAll(","," ");
           }
           sb.append("&q=").append(URLEncoder.encode(keys,"UTF-8"));
        }
        if(param.containsKey("fields")){
            sb.append("&fl=").append(URLEncoder.encode((String)param.get("fields"),"UTF-8"));
        }
        if(param.containsKey("limit")){
            String limit = (String)param.get("limit");
            sb.append("&rows=");
            if(limit.equals("max")){
                sb.append(Integer.MAX_VALUE);
            }else{
                sb.append(limit);
            }
        }
        if(param.containsKey("offset")){
            sb.append("&start=").append((String)param.get("offset"));
        }
        return sb.toString();
    }

    @RequestMapping(value = "search",method = RequestMethod.GET)
    @ResponseBody
    public String search(@RequestParam Map<String, Object> params,HttpServletResponse response) throws Exception{
        String uri = searchUrlAppend(params);
        return solrManager.excuteAndOutPut(uri);
    }

}
