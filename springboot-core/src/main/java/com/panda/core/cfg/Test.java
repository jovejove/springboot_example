package com.panda.core.cfg;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author Administrator
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
//        String dir = System.getProperty("user.dir");
//        log.info(dir);
        Map<String, String> getenv = System.getenv();
        log.info(JSON.toJSONString(getenv));
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        String jsonString = JSON.toJSONString(entries, SerializerFeature.PrettyFormat);
        log.info(jsonString);
//        System.out.println(JSON.toJSONString(properties));
    }
}
