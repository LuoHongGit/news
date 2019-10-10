package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户持久层接口
 */
public interface UserDao {
    /**
     * 通过用户名查询用户
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username = #{username}")
    @Results(value = {
            @Result(id=true,property="id",column="id"),
            @Result(property="username",column="username"),
            @Result(property="email",column="email"),
            @Result(property="password",column="password"),
            @Result(property="phoneNum",column="phoneNum"),
            @Result(property="status",column="status"),
            @Result(property="roles",column="id",javaType=java.util.List.class,many=@Many(select="com.itheima.ssm.dao.RoleDao.findRoleByUserId"))
    })
    public UserInfo findUserByUsername(String username) throws Exception;

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from users")
    public List<UserInfo> findAll() throws Exception;

    /**
     * 添加用户
     * @param userInfo
     */
    @Insert("insert into users (email,username,password,phoneNum,status,superior) values (#{email},#{username},#{password},#{phoneNum},#{status},#{superior})")
    void save(UserInfo userInfo) throws Exception;

    /**
     * 通过id查询用户
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="username",column="username"),
            @Result(property="email",column="email"),
            @Result(property="password",column="password"),
            @Result(property="phoneNum",column="phoneNum"),
            @Result(property="status",column="status"),
            @Result(property="roles",column="id",javaType=java.util.List.class,many=@Many(select="com.itheima.ssm.dao.RoleDao.findRoleByUserId"))
    })
    public UserInfo findById(String id) throws Exception;

    /**
     * 查询指定用户没有的角色
     * @param id
     * @return
     */
    @Select("select * from role where id not in (select roleId from users_role where userId = #{id})")
    public List<Role> findOtherRoles(String id) throws Exception;

    /**
     * 给用户添加角色
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role values (#{userId},#{roleId})")
    public void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId) throws Exception;
}
