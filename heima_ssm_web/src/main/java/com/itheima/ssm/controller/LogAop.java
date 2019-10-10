package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 记录日志切面类
 */
/*@Component
@Aspect*/
public class LogAop {
    //注入系统日志业务层
    @Autowired
    private SysLogService sysLogService;

    //注入request对象
    @Autowired
    private HttpServletRequest request;

    //访问时间
    private Date visitTime;
    //访问的类
    private Class clazz;
    //访问的方法
    private Method method;

    //前置通知
    @Before("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception{
        //记录当前时间
        visitTime = new Date();

        //记录要访问的类
        clazz = jp.getTarget().getClass();

        if(clazz.getName().equals("com.itheima.ssm.controller.EditorController")){
            return;
        }

        //获取访问的方法名
        String methodName = jp.getSignature().getName();

        //获取方法的参数
        Object[] args = jp.getArgs();

        //通过方法名和参数获取方法对象
        if(args == null || args.length == 0){
            method = clazz.getMethod(methodName);
        }else{
            //初始化参数类型数组
            Class[] classes = new Class[args.length];
            for(int i = 0;i < args.length;i++){
                classes[i] = args[i].getClass();
            }

            //通过方法名和参数类型数组获取方法对象
            method = clazz.getMethod(methodName,classes);
        }

    }

    //后置通知
    @After("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception{
        //计算访问时长
        long time = new Date().getTime() - visitTime.getTime();

        //拼接URL
        String url = "";
        if(clazz != null && method != null && clazz != LogAop.class && clazz != EditorController.class){
            //获取类上的一级路径
            RequestMapping classAnno = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
            if(classAnno != null){
                url += classAnno.value()[0];

                //获取方法上的二级路径
                RequestMapping methodAnno = (RequestMapping)method.getAnnotation(RequestMapping.class);
                if(methodAnno != null){
                    url += methodAnno.value()[0];
                }

            }
        }

        //获取访问者ip
        String ip = request.getRemoteAddr();

        //获取当前操作用户
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User)context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //将相关数据封装为日志对象
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);

        //调用业务层添加日志记录
        sysLogService.save(sysLog);
    }
}
