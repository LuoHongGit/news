package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.SysLogDao;
import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统日志业务层实现类
 */
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService{
    //注入持久层
    @Autowired
    private SysLogDao sysLogDao;

    /**
     * 添加日志记录
     * @param sysLog
     * @throws Exception
     */
    @Override
    public void save(SysLog sysLog) throws Exception {
        //调用持久层
        sysLogDao.save(sysLog);
    }

    /**
     * 查询所有日志
     * @return
     * @throws Exception
     */
    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
