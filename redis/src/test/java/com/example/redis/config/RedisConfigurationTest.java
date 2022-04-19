package com.example.redis.config;

import com.example.redis.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author: ljj
 * @date: 2022/4/18
 * @description:
 */

@SpringBootTest
public class RedisConfigurationTest {

    @Resource
    private RedisTemplate<String ,Object> redisTemplate;

    @Test
    void test() {
        HashMap<String , String> map = new HashMap<>();
        map.put("22你好aa", "中午呢");
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("test",new User(18,"panda",null,null));


        valueOperations.set("test2",new User(18,"panda", Collections.singletonList("game"),map));
        User user = (User) valueOperations.get("test");
        User user2 = (User) valueOperations.get("test2");
        System.out.println(user);
        System.out.println(user2);
    }
}