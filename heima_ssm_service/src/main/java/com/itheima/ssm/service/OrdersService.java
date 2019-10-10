package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;

import java.util.List;

/**
 * 订单业务层接口
 */
public interface OrdersService {

    public List<Orders> findAll(int page, int size) throws Exception;

    Orders findById(String id) throws Exception;
}
