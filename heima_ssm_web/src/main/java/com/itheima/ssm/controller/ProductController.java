package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * 商品控制层
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    //使用Spring按类型中的注入商品业务层
    @Autowired
    private ProductService productService;

    /**
     * 查询所有商品
     */
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll() throws Exception{
        //初始化Modelandview对象
        ModelAndView modelAndView = new ModelAndView();

        //调用业务层查询
        List<Product> productList = productService.findAll();

        //将结果放入request域中
        modelAndView.addObject("productList",productList);

        //设置跳转页面
        modelAndView.setViewName("product-list1");

        return modelAndView;
    }

    /**
     * 添加产品
     */
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        //调用业务层添加
        productService.save(product);

        //页面跳转
        return  "redirect:findAll.do";
    }
}
