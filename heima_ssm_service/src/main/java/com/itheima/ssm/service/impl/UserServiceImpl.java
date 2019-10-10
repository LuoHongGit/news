package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.UserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户业务层接口
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
    //使用spring自动注入用户持久层
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //初始化用户
        UserInfo userInfo = null;

        //调用持久层查询
        try {
            userInfo = userDao.findUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //创建spring-security提供的user对象
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() == 0 ? false : true,true,true,true,getAuthority(userInfo.getRoles()));

        return user;
    }

    /**
     * 返回一个集合里面存入用户描述
     */
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        //创建集合
        List<SimpleGrantedAuthority> list = new ArrayList<>();

        //加入数据
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    @Override
    public List<UserInfo> findAll() throws Exception{
        //调用持久层查询
        return userDao.findAll() ;
    }

    /**
     * 添加用户
     * @param userInfo
     */
    @Override
    public void save(UserInfo userInfo) throws Exception {
        //将用户密码加密
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));

        userDao.save(userInfo);
    }

    /**
     * 通过id查询用户
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    /**
     * 查询当前用户没有的角色
     */
    @Override
    public List<Role> findOtherRoles(String id) throws Exception{
        return userDao.findOtherRoles(id);
    }

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     * @throws Exception
     */
    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {
        //遍历角色集合
        for (String roleId : roleIds) {
            //调用持久层
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
