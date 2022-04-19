package com.example.aspect.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.aspect.annotation.OperationRecord;
import com.example.aspect.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ljj
 * @date: 2022/4/19
 * @description:
 */

@RestController
@RequestMapping("/")
public class ExportController {

    @OperationRecord
    @PostMapping("/export")
    public Object export(@RequestBody User user) {
        return "export:"+ JSONObject.toJSONString(user);
    }

    @PostMapping("/export2")
    public Object export2(@RequestBody User user) {
        return "export2:"+ JSONObject.toJSONString(user);
    }
}
