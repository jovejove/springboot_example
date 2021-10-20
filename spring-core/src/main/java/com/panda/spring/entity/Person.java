package com.panda.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    @NotNull(message = "name不能为空")
    @Size(max = 6, message = "名字过长，最大长度为6个字符")
    private String name;


    @Min(value = 1, message = "age 太小，不能为负数")
    @Max(value = 150, message = "age too big")
    private int age;

    // the usual getters and setters...
}