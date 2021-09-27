package com.panda.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalConfig {
    /**
     * 在全局配置中添加 userInfo 方法，返回一个 map。
     * 该方法有一个注解@ModelAttribute，其中 的 value 属性表示这条返回数据的 key，而方法的返回值是返回数据的 value。
     * 此时在任意请求的 Controller 中，通过方法参数中的 Model 都可以获取 info 的数据。
     */
    @ModelAttribute(value = "info")
    public Map<String, String> userInfo() {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", "罗贯中");
        map.put("gender", "男");
        return map;
    }
} 