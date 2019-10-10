package com.itheima.ssm.dao;

import com.itheima.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 系统日志持久层接口
 */
public interface SysLogDao {

    /**
     * 添加日志
     * @param sysLog
     * @throws Exception
     */
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog) throws Exception;

    /**
     * 查询所有日志
     * @return
     * @throws Exception
     */
    @Select("select * from sysLog")
    List<SysLog> findAll() throws Exception;
}
