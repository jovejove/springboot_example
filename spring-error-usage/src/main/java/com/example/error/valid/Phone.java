package com.example.error.valid;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author: ljj
 * @date: 2022/7/1
 * @description:
 */
@Data
public class Phone {
    @Size(max = 10)
    private String number;
}
