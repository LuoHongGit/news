package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 权限控制层
 */
@Controller
@RequestMapping("/permission")
public class PermissionController{
    //注入业务层
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有权限
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        //创建modelandview对象
        ModelAndView mv = new ModelAndView();

        //调用业务层
        List<Permission> permissions = permissionService.findAll();

        //将数据放入modelandview中
        mv.addObject("permissionList",permissions);

        //设置视图
        mv.setViewName("permission-list");

        return mv;
    }

    /**
     * 添加资源权限
     */
    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception{
        //调用业务层
        permissionService.save(permission);

        return "redirect:findAll.do";
    }

}
