package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 角色持久层接口
 */
public interface RoleDao {
    /**
     * 通过用户id关联查询角色
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="roleName",column="roleName"),
            @Result(property="roleDesc",column="roleDesc"),
            @Result(property="permissions",column="id",javaType=java.util.List.class,many=@Many(select="com.itheima.ssm.dao.PermissionDao.findByRoleId")),
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    /**
     * 查询所有角色
     * @return
     * @throws Exception
     */
    @Select("select * from role")
    public List<Role> findAll() throws Exception;

    /**
     * 保存角色
     * @param role
     */
    @Insert("insert into role (roleName,roleDesc) values (#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 通过id查询角色
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from role where id = #{id}")
    public Role findById(String id) throws Exception;

    /**
     * 通过id查询该角色没有的权限
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{id})")
    public List<Permission> findOtherPermissions(String id) throws Exception;

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionId
     * @throws Exception
     */
    @Insert("insert into role_permission values (#{permissionId},#{roleId})")
    public void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId) throws Exception;
}
