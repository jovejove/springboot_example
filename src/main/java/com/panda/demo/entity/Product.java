package com.panda.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName: Product.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-08-11
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private Long id;//商品唯一标识
    private String title;//商品名称
    private String category;//分类名称
    private Double price;//商品价格
    private String images;//图片地址
}
