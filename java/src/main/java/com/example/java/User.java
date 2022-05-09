package com.example.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ljj
 * @date: 2022/5/9
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private Integer age;
    private Address address;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address{
        private String city;
    }
}
