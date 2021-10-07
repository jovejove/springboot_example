package com.panda.product.service;

import com.panda.product.entity.Product;

/**
 * @Author: jjl
 * @Description:
 * @Date: 2021-10-08
 * @Version: 1.0
 */
public interface ProductService {
    /**
     * 根据id查询
     */
    Product findById(Long id);

    /**
     * 保存
     */
    void save(Product product);

    /**
     * 更新
     */
    void update(Product product);

    /**
     * 删除
     */
    void delete(Long id);
}
