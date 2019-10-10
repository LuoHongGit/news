package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * 会员业务层
 */
public interface MemberDao {
    /**
     * 通过id查询会员
     */
    @Select("select * from member where id = #{id}")
    public Member findById(String id) throws Exception;
}
