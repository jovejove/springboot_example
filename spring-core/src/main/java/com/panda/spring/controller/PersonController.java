package com.panda.spring.controller;

import com.panda.spring.entity.Person;
import com.panda.spring.entity.PersonForm;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/person")
@RestController
public class PersonController {

    @PostMapping("/test")
    public Object test(@RequestBody @Validated Person person) {
        Map<String, Object> map = new HashMap<>();
        // 如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
//        if (result.hasErrors()) {
//            List<String> res = new ArrayList<>();
//            result.getFieldErrors().forEach(error -> {
//                String field = error.getField();
//                Object value = error.getRejectedValue();
//                String msg = error.getDefaultMessage();
//                res.add(String.format("错误字段 -> %s 错误值 -> %s 原因 -> %s", field, value, msg));
//            });
//            map.put("msg", res);
//            return map;
//        }
        map.put("msg", "success");
        System.out.println(person);
        return map;
    }


    @GetMapping("/test2")
    public Object test2(@Validated PersonForm person, BindingResult result) {
        Map<String, Object> map = new HashMap<>();
        // 如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
        if (result.hasErrors()) {
            List<String> res = new ArrayList<>();
            result.getFieldErrors().forEach(error -> {
                String field = error.getField();
                Object value = error.getRejectedValue();
                String msg = error.getDefaultMessage();
                res.add(String.format("错误字段 -> %s 错误值 -> %s 原因 -> %s", field, value, msg));
            });
            map.put("msg", res);
            return map;
        }
        map.put("msg", "success");
        System.out.println(person);
        return map;
    }

}
