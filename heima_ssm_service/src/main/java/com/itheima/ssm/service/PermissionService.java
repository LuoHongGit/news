package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限业务层接口
 */
@Service
@Transactional
public interface PermissionService {

    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;
}
