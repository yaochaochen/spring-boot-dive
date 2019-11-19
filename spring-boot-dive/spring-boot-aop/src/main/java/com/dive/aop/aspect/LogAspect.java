package com.dive.aop.aspect;


import com.dive.aop.annotation.Log;
import com.dive.aop.dao.impl.SysLogRepository;
import com.dive.aop.domain.SysLog;
import com.dive.aop.util.HttpContextUtils;
import com.dive.aop.util.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private SysLogRepository sysLogRepository;

    @Pointcut("@annotation(com.dive.aop.annotation.Log)")
    public void pointcut() {
    }

    @Pointcut(value = "pointcut()")
    public void around(ProceedingJoinPoint point) {

        long beginTime = System.currentTimeMillis();
        try {
            point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - beginTime;

        saveLog(point, time);
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            //注解的描述
            sysLog.setOperation(logAnnotation.value());
        }
        //请求方法
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        //请求参数
        Object[] args = joinPoint.getArgs();
        //请求方法参数名称
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] paramsNames = discoverer.getParameterNames(method);
        if (args != null && paramsNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += " " +paramsNames[i] + ":" + args[i];

            }
            sysLog.setParams(params);
        }
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP
        sysLog.setIp(IPUtils.getIpAddr(request));
        sysLog.setTime((int) time);
        Date date = new Date();
        sysLog.setCreateTime(date);
        //保存到数据库
        sysLogRepository.save(sysLog);

    }

}
