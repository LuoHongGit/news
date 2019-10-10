package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 商品持久层接口
 */
public interface ProductDao {
    //查询所有商品信息
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    //保存商品
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values (#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product) throws Exception;

    //根据id查询商品
    @Select("select * from product where id = #{id}")
    public Product findById(String id) throws Exception;
}