package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.RoleDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色业务层接口
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService{
    //注入持久层
    @Autowired
    private RoleDao roleDao;

    /**
     * 查询所有角色
     */
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    /**
     * 保存角色
     * @param role
     * @throws Exception
     */
    @Override
    public void save(Role role) throws Exception {
        //调用持久层
        roleDao.save(role);
    }

    /**
     * 通过id查询角色
     * @param id
     * @return
     */
    @Override
    public Role findById(String id) throws Exception{
        return roleDao.findById(id);
    }

    /**
     * 查找当前角色没有的权限
     */
    @Override
    public List<Permission> findOtherPermissions(String id) throws Exception{
        return roleDao.findOtherPermissions(id);
    }

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionIds
     * @throws Exception
     */
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        //遍历
        for (String permissionId : permissionIds) {
            //调用持久层
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
