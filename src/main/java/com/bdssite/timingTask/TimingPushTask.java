package com.bdssite.timingTask;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bdssite.modules.searchmanage.SolrManager;
import com.bdssite.modules.usermanage.entity.ShortMessage;
import com.bdssite.modules.usermanage.entity.UserInfo;
import com.bdssite.modules.usermanage.services.ShortMessageService;
import com.bdssite.modules.usermanage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Joey_Tsai on 2017/12/12.
 */
@Component
public class TimingPushTask {
    @Autowired
    private UserService userService;
    @Autowired
    private ShortMessageService shortMessageService;
    @Autowired
    private SolrManager solrManager;
    static int index=0;
    @Scheduled(cron="0 0 0 * * ?")
    public void cronJob(){
        UserInfo admin =  userService.findByUsername("admin");
        int count =(int)userService.count();

        for (int i=0;i<count/100+1;i++){
            index+=100;
            int offset=i*100;
            int limit=100;
            if (count%100!=0&&i==count/100){
                limit=count%100;
            }
            Thread thread = new PushThread(admin,offset,limit);
            thread.start();
        }

    }
    class PushThread extends Thread{
        private int offset;
        private int limit;
        private UserInfo admin;
        PushThread(UserInfo admin,int offset,int limit){
            this.offset=offset;
            this.limit=limit;
            this.admin = admin;
        }
        public void run(){
            List<UserInfo> list=userService.queryAllUserInfoPaging(limit,offset).getContent();
            for (UserInfo user:list){
                if(user.getPushUrl()==null||user.getPushUrl().equals("")||user.getUid()==admin.getUid())continue;
                ShortMessage shortMessage = new ShortMessage();
                String jsonStr = solrManager.excuteAndOutPut(user.getPushUrl());


                JSONObject object = JSON.parseObject(jsonStr);
                JSONArray data = (JSONArray) ((JSONObject) object.get("response")).getJSONArray("docs");

                if(((String)((JSONObject)data.get(0)).get("url")).equals(user.getPushUrl())) {
                    shortMessage.setContent(appendDiv((JSONObject)data.get(0)).toString());
                }
                else
                    shortMessage.setContent(appendDiv((JSONObject)data.get(0)).toString());
                shortMessage.setFromUser(admin);
                shortMessage.setToUser(user);
                shortMessageService.save(shortMessage);
                System.out.println(admin.getName()+" to "+user.getName());
            }
        }
    }
    public StringBuilder appendDiv(JSONObject object){
        StringBuilder html = new StringBuilder();
        html.append("<div class=\"sr-item\">");
        html.append("<div class=\"col-md-2\">");
        html.append("</div>");
        html.append("<div class=\"col-md-8\">");
        html.append("<a href=\""+object.get("url")+"\" class=\"sr-item-title\">职位：");
        html.append((String)object.getJSONArray("job_name").get(0));
        html.append("</a>");

        html.append("<div class=\"row\">");
        html.append("<div class=\"row\">");
        html.append("<p>公司：");
        html.append((String)object.getJSONArray("job_company").get(0));
        html.append("</p>");
        html.append("</div>");
        html.append("<div class=\"row\">");
        html.append("<p style=\"padding-left:0px\" class=\"col-md-3\">年薪：");
        if(((String)object.getJSONArray("job_negotiation").get(0)).equals("false")){
            if(((BigDecimal)object.get("job_l_highest")).doubleValue()==(((BigDecimal)object.get("job_l_lowest"))).doubleValue()){
                html.append(Double.parseDouble((String) object.get("job_l_lowest"))/10000.0);
                html.append("-");
                html.append(((BigDecimal)object.get("job_l_highest")).doubleValue()/10000.0);
            }else{
                html.append(((BigDecimal)object.get("job_l_lowest")).doubleValue()/10000.0);
            }
            html.append("万元/年");
        }else {
            html.append("面议");
        }
        html.append("</p>");
        html.append("<p class=\"col-md-3\">工作地点：");
        html.append((String)object.getJSONArray("job_place").get(0));
        html.append("</p>");
        html.append("<div class=\"row\">");
        html.append("<p style=\"padding-left:0px\" class=\"col-md-3\">学历：");
        html.append((String)object.getJSONArray("job_study").get(0));
        html.append("</p>");
        html.append("<p class=\"col-md-3\">经验：" + (String)object.getJSONArray("job_experience").get(0) + "</p>");
        html.append("</div>");
        html.append("</div>");
        html.append("<a href=\""+((String) object.get("url"))+"\" class=\"sr-item-link\">");
        html.append(((String) object.get("url")));
        html.append("</a>");
        html.append("<p class=\"sr-item-links\"><a href=\""+(String)object.get("url")+"\">Into this page</a> - <a href=\""+object.get("url")+"\">View cache</a></p>");
        html.append("</div>");
        return  html;
    }


}
