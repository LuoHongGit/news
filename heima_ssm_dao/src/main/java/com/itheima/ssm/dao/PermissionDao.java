package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 权限持久层接口
 */
public interface PermissionDao {
    @Select("select * from permission where id in (select permissionid from role_permission where roleid = #{roleid})")
    public List<Permission> findByRoleId(String roleId);

    /**
     * 查询所有权限
     * @return
     * @throws Exception
     */
    @Select("select * from permission")
    public List<Permission> findAll() throws Exception;

    /**
     * 添加资源权限
     * @param permission
     * @throws Exception
     */
    @Insert("insert into permission (permissionName,url) values (#{permissionName},#{url})")
    void save(Permission permission) throws Exception;
}
