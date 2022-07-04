package com.example.error.valid;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Data
public class Student {
    @Size(max = 10,message = "fasdfdsf")
    private String name;
    private short age;

    /**
     * 级联校验
     */
    @Valid
    private Phone phone;

}