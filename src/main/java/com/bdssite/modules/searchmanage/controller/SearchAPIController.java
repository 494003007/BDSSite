package com.bdssite.modules.searchmanage.controller;



import com.bdssite.modules.common.RequestStatus;
import com.bdssite.modules.common.dto.ListDto;
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
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

    private String searchUrlAppend(Map<String,Object> param){
        String baseSearchUrl = "http://localhost:8080/solr/collection1/findJob";
        StringBuilder sb = new StringBuilder(baseSearchUrl);
        if(param.size()!=0){
            sb.append('?');
        }
        if(param.containsKey("keyWord")){
           sb.append("q=").append((String) param.get("keyWord"));
        }
        return sb.toString();
    }
    @RequestMapping(value = "search",method = RequestMethod.GET)
    public void search(@RequestParam Map<String, Object> params,HttpServletResponse response){


        try {
            URL url = new URL(searchUrlAppend(params));
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(false);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();

            InputStream is = httpUrlConn.getInputStream();
            OutputStream os = response.getOutputStream();

            byte[] temp = new byte[1024];
            int length;
            while ((length = is.read(temp)) != -1) {
                os.write(temp, 0, length);
            }
            // 释放资源
            is.close();

            httpUrlConn.disconnect();
            os.flush();
            os.close();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
