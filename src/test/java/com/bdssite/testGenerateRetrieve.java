package com.bdssite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Encoder;

import java.util.UUID;

/**
 * Created by Ed_cc on 2017/5/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class testGenerateRetrieve {
    @Test
    public void test(){
        String secretKey = UUID.randomUUID().toString();
        System.out.println(new BASE64Encoder().encode(secretKey.getBytes()).substring(0, ((int) (Math.random()*2)+8)));
    }

    public static void main(String[] args) {
        String secretKey = UUID.randomUUID().toString();
        System.out.println(new BASE64Encoder().encode(secretKey.getBytes()).substring(0, ((int) (Math.random()*6)+8)));
    }
}
