package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

/**
 * 角色业务层
 */
public interface RoleService {

    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(String id) throws Exception;

    List<Permission> findOtherPermissions(String id) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
