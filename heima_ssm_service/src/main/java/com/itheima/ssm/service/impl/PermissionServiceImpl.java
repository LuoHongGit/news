package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.PermissionDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限业务层实现类
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService{
    //注入持久层
    @Autowired
    private PermissionDao permissionDao;

    /**
     * 查询所有权限
     * @return
     * @throws Exception
     */
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    /**
     * 添加资源权限
     * @param permission
     * @throws Exception
     */
    @Override
    public void save(Permission permission) throws Exception {
        //调用持久层
        permissionDao.save(permission);
    }
}
