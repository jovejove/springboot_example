package com.example.redis.entity;

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

    private Map<String ,String > map;
}
