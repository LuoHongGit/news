package com.itheima.ssm.service;

import com.itheima.ssm.domain.Product;

import java.util.List;

/**
 * 商品业务层实现类
 */
public interface ProductService {

    //查询所有商品
    public List<Product> findAll() throws Exception;

    //保存商品
    void save(Product product) throws Exception;
}
