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
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


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
   // @Transactional
   // @Rollback
    public void userServiceTest() {


    }

    @Test
    @Transactional
    @Rollback
    public void majorTest(){
        System.out.println("11111111111111111111");

    }

    @Test
    @Transactional
    @Rollback
    public void shortMessageServiceTest() {
        System.out.println("22222222222222222222");
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


    @Test
    public void testFollowing(){
        List<UserInfo> page= userService.queryAllUserFollowing(3,0);
        System.out.printf("test---------");
    }
}
