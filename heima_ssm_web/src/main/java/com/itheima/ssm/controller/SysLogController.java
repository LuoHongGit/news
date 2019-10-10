package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 系统日志控制层
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    //注入业务层
    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("findAll.do")
    public ModelAndView findAll() throws Exception{
        //初始化modelandview对象
        ModelAndView mv = new ModelAndView();

        //调用业务层查询所有日志
        List<SysLog> sysLogs = sysLogService.findAll();

        //将数据放入Modelandview
        mv.addObject("sysLogs",sysLogs);

        //设置视图
        mv.setViewName("syslog-list");

        return mv;
    }
}
