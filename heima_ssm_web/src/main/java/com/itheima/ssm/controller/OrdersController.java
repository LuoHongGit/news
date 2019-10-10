package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 订单控制层
 */
@Controller("ordersController")
@RequestMapping("/orders")
public class OrdersController {
    //使用Spring按类型自动注入订单业务层
    @Autowired
    private OrdersService ordersService;

    /**
     * 查询所有订单(未分页)
     */
    /*@RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        //初始化Modelandview对象
        ModelAndView mav = new ModelAndView();

        //调用业务层查询
        List<Orders> ordersList = ordersService.findAll();

        System.out.println(ordersList.size());

        //将数据放入request
        mav.addObject("ordersList",ordersList);

        //设置视图名称
        mav.setViewName("orders-list");

        return mav;
    }*/

    /**
     * 分页查询所有订单
     */
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(value="page",required=true,defaultValue="1") Integer page,@RequestParam(value="size",required=true,defaultValue="4") Integer size) throws Exception{
        //初始化Modelandview对象
        ModelAndView mav = new ModelAndView();

        //调用业务层查询
        List<Orders> ordersList = ordersService.findAll(page,size);

        //创建分页对象
        PageInfo<Orders> pageInfo = new PageInfo<>(ordersList);

        //将数据放入request
        mav.addObject("pageInfo",pageInfo);

        //设置视图名称
        mav.setViewName("orders-page-list");

        return mav;
    }

    /**
     * 根据id查询订单
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam("id") String id) throws Exception{
        //初始化Modelandview对象
        ModelAndView mv = new ModelAndView();

        //调用业务层查询
        Orders orders = ordersService.findById(id);

        //将数据放入request
        mv.addObject("orders",orders);

        //设置视图名称
        mv.setViewName("orders-show");

        return mv;
    }

}
