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

    @NotNull
    @Size(max=6,message = "to long")
    private String name;

    @Min(0)
    @Max(value = 150,message = "too big")
    private int age;

    // the usual getters and setters...
}