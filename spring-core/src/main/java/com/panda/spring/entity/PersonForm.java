package com.panda.spring.entity;



import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Data
@ToString
public class PersonForm {
    @NotNull
    @Size(max=64)
    private String name;

    @Min(0)
    @Max(value = 130,message = "130 aaa")
    private int age;
}