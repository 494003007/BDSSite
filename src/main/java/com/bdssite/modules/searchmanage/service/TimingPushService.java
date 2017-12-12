package com.bdssite.modules.searchmanage.service;

import com.bdssite.modules.usermanage.entity.ShortMessage;
import com.bdssite.modules.usermanage.entity.UserInfo;
import com.bdssite.modules.usermanage.services.ShortMessageService;
import com.bdssite.modules.usermanage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Joey_Tsai on 2017/12/12.
 */
@Component
public class TimingPushService {
    @Autowired
    private UserService userService;
    @Autowired
    private ShortMessageService shortMessageService;
    @Scheduled(cron="5 * * * * ?")
    public void cronJob(){
        UserInfo admin =  userService.findByUsername("admin");
        int count =(int)userService.count();

        for (int i=0;i<count/10+1;i++){

            int offset=i*10;
            int limit=10;
            if (count%10!=0&&i==count/10){
                limit=count%10;
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
                ShortMessage shortMessage = new ShortMessage();
                shortMessage.setContent("推送内容");
                shortMessage.setFromUser(admin);
                shortMessage.setToUser(user);
                shortMessageService.save(shortMessage);
                System.out.println(admin.getName()+" to "+user.getName());
            }
        }
    }
}
