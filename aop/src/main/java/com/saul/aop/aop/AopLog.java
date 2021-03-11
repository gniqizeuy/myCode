package com.saul.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Aspect 使之成为切面类
 * Component 把切面类放入 IoC 容器
 *
 */
@Aspect
@Component
public class AopLog {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 线程局部的变量，用于解决多线程中相同变量的访问冲突问题
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    // 定义切点
    @Pointcut("execution(public * com.saul..*.*(..))")
    public void aopWebLog() {

    }

    @Before("aopWebLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL：" + request.getRequestURI());
        logger.info("HTTP 方法：" + request.getMethod());
        logger.info("IP 地址：" + request.getRemoteAddr());
        logger.info("类的方法：" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("参数：" + request.getQueryString());
    }

    @AfterReturning(pointcut = "aopWebLog()", returning = "retObject")
    public void doAfterReturning(Object retObject) throws Throwable {
        // 处理完请求，返回内容
        logger.info("应答值：" + retObject);
        logger.info("费时：" + (System.currentTimeMillis() - startTime.get()));
    }

    // 方法抛出异常退出时执行的通知
    @AfterThrowing(pointcut = "aopWebLog()", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, Exception ex) {
        logger.error("执行 " + " 异常", ex);
    }
}
