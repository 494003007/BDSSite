package com.bdssite;

import com.bdssite.modules.searchmanage.entity.Major;
import com.bdssite.modules.searchmanage.service.MajorService;
import com.bdssite.modules.usermanage.entity.ShortMessage;
import com.bdssite.modules.usermanage.entity.UserInfo;
import com.bdssite.modules.usermanage.services.ShortMessageService;
import com.bdssite.modules.usermanage.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by D on 2017/2/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CRUDTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private ShortMessageService shortMessageService;

    @Test
    @Transactional
    @Rollback
    public void userServiceTest() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("testaaaaaaaa");
        userInfo.setPassword("sasasa");
        userInfo.setState((byte)0);
        userInfo.setSalt("aaaaaa");
        userInfo.setEmail("aaa@qqqq.aaa");
        userInfo.setName("cai");
        userService.save(userInfo);

        UserInfo cai = userService.findByName("cai");

        System.out.println("userServiceTest:" + cai);

        Assert.assertNotNull(cai.getName());
    }

    @Test
    @Transactional
    @Rollback
    public void majorTest(){
        Major parentMajor = new Major();
        parentMajor.setName("父学科");
        Major childMajor = new Major();
        childMajor.setName("子学科1");
        Major childMajor2 = new Major();
        childMajor2.setName("子学科2");
        majorService.save(parentMajor);
        childMajor.setParentMajor(parentMajor);
        childMajor2.setParentMajor(parentMajor);
        majorService.save(childMajor);
        majorService.save(childMajor2);


    }

    @Test
    @Transactional
    @Rollback
    public void shortMessageServiceTest() {
        UserInfo userInfo = userService.findByName("cai");
        List<ShortMessage> shortMessages = shortMessageService.findByToUserAndIsRead(userInfo,1);
        removeDuplicate(shortMessages);
        System.out.println("----------------------------");
    }

    public  void  removeDuplicate(List<ShortMessage> list)  {
        for  ( int  i  =   list.size()  -   1 ; i  >=  0; i -- )  {
            for  ( int  j  =  i+1 ; j  <  list.size()  ; j ++ )  {
                if  (list.get(j).getFromUser().equals(list.get(i).getFromUser()))  {
                    list.remove(j);
                }
            }
        }

    }
}
