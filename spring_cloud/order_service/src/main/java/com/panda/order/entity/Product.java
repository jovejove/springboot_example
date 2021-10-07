package com.panda.order.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: jjl
 * @Description: 商品实体类
 * @Date: 2021-10-08
 * @Version: 1.0
 */

@Data
public class Product {

    private Long id;
    private String productName;
    private Integer status;
    private BigDecimal price;
    private String productDesc;
    private String caption;
    private Integer inventory;
}
