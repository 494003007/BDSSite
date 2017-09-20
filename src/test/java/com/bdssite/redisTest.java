package com.bdssite;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

/**
 * Created by Ed_cc on 2017/9/8.
 */
public class redisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

//    @Test
//    public void test() throws Exception {
//        stringRedisTemplate.opsForValue().set("aaa", "111");
//        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
//    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
//        jedis.auth("");
        jedis.set("age", "1");
        System.out.println(jedis.get("age"));
    }
}
