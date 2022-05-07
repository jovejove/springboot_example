package com.example.aspect.entity;

import com.alibaba.fastjson.JSONObject;
import com.example.aspect.annotation.OperationRecordDataInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author: ljj
 * @date: 2022/4/18
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer age;

    private String name;

    private List<String> habit;

    private Map<String, String> map;

    @OperationRecordDataInfo(field = "studentId")
    private String studentId;

    @OperationRecordDataInfo(field = "studentName")
    private String studentName;

    @OperationRecordDataInfo(field = "studentPhone")
    private String studentPhone;

    @OperationRecordDataInfo(field = "studentCardNo")
    private String studentCardNo;

    private List<Long> ids;

    public static void main(String[] args) {
        String json = "[1,2,3,6,5]";

        List<Long> list = JSONObject.parseObject(json, List.class);

        System.out.println(list);

    }
}
