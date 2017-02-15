package com.recordme;

import com.recordme.modules.usermanage.entity.UserInfo;
import com.recordme.modules.usermanage.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by D on 2017/2/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CRUDTest {

    @Autowired
    private UserService userService;

    @Test
    public void userServiceTest() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("testaaaaaaaa");
        userInfo.setPassword("sasasa");
        userInfo.setState((byte)0);
        userService.save(userInfo);

        System.out.println(userService.findByUsername("testaaaaaaaa"));

        userService.deleteAll();
    }
}
