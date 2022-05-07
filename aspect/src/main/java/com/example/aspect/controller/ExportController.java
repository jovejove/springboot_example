package com.example.aspect.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.aspect.annotation.OperationRecord;
import com.example.aspect.annotation.OperationRecordDataInfo;
import com.example.aspect.entity.User;
import com.example.aspect.entity.UserEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author: ljj
 * @date: 2022/4/19
 * @description:
 */

@RestController
@RequestMapping("/")
public class ExportController {

    @OperationRecord(operationDesc = "新增用户")
    @PostMapping("/export")
    public Object export(@RequestBody User user) throws Exception {
        if (true) {
            throw new Exception("哈哈哈哈哈");
        }
        return "export:" + JSONObject.toJSONString(user);
    }

    @OperationRecord(operationDesc = "新增用户")
    @PostMapping("/export2")
    public Object export2(@RequestBody User user) {
        System.out.println("export2");
        this.middle();
        return "export2:" + JSONObject.toJSONString(user);
    }

    /**
     * 不会生效，因为没有被spirng代理
     *
     * @return
     */
    @OperationRecord(operationDesc = "新增用户")
    public Object middle() {
        System.out.println("middle");

        return "middle";
    }

    public static void main(String[] args) throws IllegalAccessException {
        User user = new User();
        user.setStudentId("0001");
        user.setStudentName("哈哈哈");
        List list = Collections.singletonList(user);

        List insertList = new ArrayList();
        for (Object object : list) {

            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                OperationRecordDataInfo annotation = field.getAnnotation(OperationRecordDataInfo.class);
                UserEntity userEntity = null;
                if (Objects.nonNull(annotation)) {
                    userEntity = new UserEntity();
                    if ("studentId".equals(annotation.field())) {
                        field.setAccessible(true);
                        String studentId = (String) field.get(object);
                        userEntity.setStudentId(studentId);
                    }
                }
                if (Objects.nonNull(userEntity)) {
                    insertList.add(userEntity);
                }
            }
        }

        System.out.println(JSONObject.toJSONString(insertList));
    }
}
