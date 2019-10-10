package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.ProductDao;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品业务层实现类
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService{
    //使用Spring按类型注入商品持久层
    @Autowired
    private ProductDao productDao;

    /**
     * 查询所有商品
     */
    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    /**
     * 保存商品
     * @param product
     */
    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }
}
