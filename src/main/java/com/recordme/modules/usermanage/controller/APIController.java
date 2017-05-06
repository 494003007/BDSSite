package com.recordme.modules.usermanage.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Map;


/**
 * Created by D on 2017/5/6.
 */
@RequestMapping("api")
public class APIController {
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
    @RequestMapping(value = "search",method = RequestMethod.POST)
    @ResponseBody
    public String search(@RequestParam Map<String, Object> params){

        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(searchUrlAppend(params));
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(false);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                builder.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        System.out.println(builder.toString());
        return builder.toString();



    }
}