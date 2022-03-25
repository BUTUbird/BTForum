package org.butu.common.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.butu.common.annotation.SystemLog;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @program: BTForum
 * @description: AOP日志
 * @packagename: org.butu.common.aspect
 * @author: BUTUbird
 * @date: 2022-03-25 22:59
 **/
@Component
@Aspect
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(org.butu.common.annotation.SystemLog)")
    public void pt(){

    }
    @Around("pt()")
    public Object  printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);
        } finally {
            //结束后换行
            log.info("=======End======="+ System.lineSeparator());
        }

        return ret;
    }

    private void handleAfter(Object ret) {
        //打印出参
        log.info("Response      :{}",JSON.toJSONString(ret));

    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取被增强方法的注解方法
        SystemLog systemLog = getSystemLog(joinPoint);


        log.info("=======Start=======");
        //打印请求URL
        log.info("URL           :{}",request.getRequestURI());
        //打印描述信息
        log.info("BusinessName     :{}",systemLog.businessName());
        //打印Http method
        log.info("HTTP Method      :{}",request.getMethod());
        //打印调用controller的全路径以及执行方法
        log.info("Class Method      :{}.{}",joinPoint.getSignature().getDeclaringTypeName(),((MethodSignature)joinPoint.getSignature()).getName());
        //打印请求的IP
        log.info("IP           :{}",request.getRemoteHost());
        //打印请求入参
        log.info("Request        :{}", JSON.toJSONString(joinPoint.getArgs()));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature  methodSignature = (MethodSignature) joinPoint.getSignature();
        SystemLog systemLog = methodSignature.getMethod().getAnnotation(SystemLog.class);
        return systemLog;
    }
}
