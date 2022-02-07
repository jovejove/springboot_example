package com.panda.config;

import com.alibaba.fastjson.JSON;
import com.panda.excel.upload.UploadData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;
import javax.lang.model.element.VariableElement;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisConfigTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Test
    void hashOperations() {
        HashOperations<String, Object, Object> opsForHash = stringRedisTemplate.opsForHash();
        for (int i = 0; i < 3; i++) {
            opsForHash.put("hash", "hash" + i, "hash" + i);
            System.out.println(opsForHash.get("hash", "hash" + i));
        }
        List<Object> hash = opsForHash.values("hash");
        System.out.println(JSON.toJSONString(hash));

        Map<Object, Object> entries = opsForHash.entries("hash");
        System.out.println(JSON.toJSONString(entries));

        stringRedisTemplate.expire("hash", 60, TimeUnit.MINUTES);


        System.out.println();
    }

    @Test
    void valueOperations() {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        for (int i = 0; i < 3; i++) {
            opsForValue.set("value" + i, i + "", 60, TimeUnit.SECONDS);
            System.out.println(opsForValue.get("value" + i));
        }

        ValueOperations<String, Object> forValue = redisTemplate.opsForValue();
        UploadData uploadData = new UploadData();
        uploadData.setDate(new Date());
        uploadData.setString("哈哈哈");
        uploadData.setBigDecimal(BigDecimal.TEN);
        uploadData.setErrorInfoList(Collections.singletonList("错误"));
        HashMap<Integer, String > map = new HashMap<>();
        map.put(22, "嘿嘿");
//        uploadData.setErrorInfoMap(map);
        forValue.set("test:hello:1", uploadData);
        System.out.println(JSON.toJSONString(uploadData));
        Object o = forValue.get("test:hello:1");
        System.out.println(JSON.toJSONString(o));
        UploadData uploadData1 = (UploadData) o;
        System.out.println(JSON.toJSONString(uploadData1));
    }

    @Test
    void listOperations() {

    }

    @Test
    void setOperations() {
    }

    @Resource
    private ZSetOperations<String, Object> zSetOperations;

    @Test
    void zSetOperations() {
        zSetOperations.add("name", "panda", 99);
        zSetOperations.add("name", "piggy", 99.99);

        Object name1 = zSetOperations.randomMember("name");
        System.out.println(JSON.toJSONString(name1));
        ZSetOperations.TypedTuple<Object> name = zSetOperations.popMax("name");
        System.out.println(JSON.toJSONString(name));
    }
}