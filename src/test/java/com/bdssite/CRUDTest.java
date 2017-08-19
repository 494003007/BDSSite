package com.bdssite;

import com.bdssite.modules.usermanage.entity.ShortMessage;
import com.bdssite.modules.usermanage.entity.UserInfo;
import com.bdssite.modules.usermanage.services.ShortMessageService;
import com.bdssite.modules.usermanage.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
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
    private ShortMessageService shortMessageService;
    @Test
    public void userServiceTest() {
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUsername("testaaaaaaaa");
//        userInfo.setPassword("sasasa");
//        userInfo.setState((byte)0);
//        userInfo.setName("cai");
//        userService.save(userInfo);
//
//        System.out.println("----------------"+userService.findByName("cai"));
//
//        userService.delete(userInfo.getUid());
        Collection<Long> list = new ArrayList();
        list.add(Long.valueOf(1));
        list.add(Long.valueOf(2));
//        list.add(Long.valueOf(3));

//        aaaa = list.toArray(aaaa);
        shortMessageService.deleteByIdIn(list);
//        shortMessageService.delete((long) 1);
        System.out.println("111111111111111111111111");
    }
}
