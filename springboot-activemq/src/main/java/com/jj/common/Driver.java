package com.jj.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Driver.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-25
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver implements Serializable {
    private Long id;
    private String name;
}
