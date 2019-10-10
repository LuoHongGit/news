package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 角色控制层
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    //注入业务层
    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        //初始化ModelAndview对象
        ModelAndView mv = new ModelAndView();

        //调用业务层
        List<Role> roles = roleService.findAll();

        //将数据放入modelandview中
        mv.addObject("roleList",roles);

        //设置视图
        mv.setViewName("role-list");

        return mv;
    }

    /**
     * 添加角色
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping("save.do")
    public String save(Role role) throws Exception{
        //调用业务层查询
        roleService.save(role);

        return "redirect:findAll.do";
    }

    /**
     * 通过id查询角色和角色没有的权限
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(value="id",required=true) String id) throws Exception{
        //初始化Modelandview对象
        ModelAndView mv = new ModelAndView();

        //通过id查询角色
        Role role = roleService.findById(id);

        //通过id查询角色没有的权限
        List<Permission> otherPermissions = roleService.findOtherPermissions(id);

        //将数据放入modelandview
        mv.addObject("role",role);
        mv.addObject("permissionList", otherPermissions);

        //设置视图
        mv.setViewName("role-permission-add");

        return mv;
    }

    /**
     * 给角色添加权限
     */
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(value="roleId",required=true) String roleId,@RequestParam(value="ids",required=true) String[] permissionIds) throws Exception{
        //调用业务层
        roleService.addPermissionToRole(roleId,permissionIds);

        return "redirect:findAll.do";
    }

}