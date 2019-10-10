package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 用户控制层
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController {
    //使用spring自动注入用户业务层
    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll() throws Exception{
        //初始化Modelandview对象
        ModelAndView mv = new ModelAndView();

        //获取查询结果
        List<UserInfo> users = userService.findAll();

        //将数据封装到Modelandview中
        mv.addObject("userList",users);

        //设置跳转路径
        mv.setViewName("user-list");

        return mv;
    }

    /**
     * 添加用户
     */
    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username == 'tom'")
    public String save(UserInfo userInfo) throws Exception{
        //调用业务层保存用户
        userService.save(userInfo);

        //请求重定向
        return "redirect:findAll.do";
    }

    /**
     * 查询指定id用户
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
        //初始化Modelandview对象
        ModelAndView mv = new ModelAndView();

        //调用业务层查询
        UserInfo userInfo = userService.findById(id);

        //将数据放入modelandview
        mv.addObject("user",userInfo);

        //设置视图
        mv.setViewName("user-show1");

        return mv;
    }

    /**
     * 给用户添加角色
     */
    @RequestMapping("findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(value="id",required=true) String id) throws Exception{
        //初始化modelandview对象
        ModelAndView mv = new ModelAndView();

        //调用业务层
        UserInfo user = userService.findById(id);

        List<Role> roleList = userService.findOtherRoles(id);

        //将数据放入modelandview
        mv.addObject("user",user);
        mv.addObject("roleList",roleList);

        //设置视图
        mv.setViewName("user-role-add");

        return mv;
    }

    /**
     * 给用户添加角色
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(value="userId",required=true) String userId,@RequestParam(value="ids",required=true) String[] roleIds) throws Exception{
        //调用业务层
        userService.addRoleToUser(userId,roleIds);

        return "redirect:findAll.do";
    }

    /**
     * 查询数据并跳转到添加用户界面
     */
    @RequestMapping("/forwardAddUser.do")
    public ModelAndView forwardAddUser() throws Exception{
        //创建模型视图对象
        ModelAndView mv = new ModelAndView();

        //获取所有用户信息
        List<UserInfo> userList = userService.findAll();

        //将数据添加到模型视图中
        mv.addObject("userList",userList);

        //设置视图
        mv.setViewName("user-add");

        return mv;
    }

}
