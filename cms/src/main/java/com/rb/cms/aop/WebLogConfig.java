package com.rb.cms.aop;

import com.common.base.util.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 全局切面
 *
 * @author xujiping
 * @date 2018/8/10 13:52
 */
@Aspect
@Component
public class WebLogConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.rb.cms.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

    @AfterReturning(returning = "result", pointcut = "webLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object result) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        String ip = IpUtils.getIpAddr(request);
        logger.info("******IP : " + ip);
        logger.info("******URL : " + request.getRequestURL().toString());
        logger.info("******HTTP_METHOD : " + request.getMethod());
        logger.info("******CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint
                .getSignature().getName());
        logger.info("******ARGS : " + Arrays.toString(joinPoint.getArgs()));
        // 处理完请求，返回内容
        logger.info("******RESPONSE : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint
                .getSignature().getName() + ": " + result);
        System.out.println("******IP : " + ip);
        System.out.println("******URL : " + request.getRequestURL().toString());
        System.out.println("******HTTP_METHOD : " + request.getMethod());
        System.out.println("******CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName());
        System.out.println("******ARGS : " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("******RESPONSE : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint
                .getSignature().getName() + ": " + result);

    }
}
