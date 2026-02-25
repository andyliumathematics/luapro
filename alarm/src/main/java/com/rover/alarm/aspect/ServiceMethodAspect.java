package com.rover.alarm.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Service层方法切面
 * 拦截所有@Service注解类中的方法执行
 */
@Aspect
@Component
public class ServiceMethodAspect {

    private static final Logger logger = LoggerFactory.getLogger(ServiceMethodAspect.class);

    /**
     * 定义切入点：匹配所有@Service注解的类中的所有方法
     */
    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void serviceMethods() {}

    /**
     * 环绕通知：拦截所有@Service类的方法执行
     */
    @Around("serviceMethods()")
    public Object aroundServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法信息
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        // 方法执行前的日志记录
        logger.info("┌─────────────────────────────────────────────────────────");
        logger.info("│ 开始执行服务方法");
        logger.info("│ 类名: {}", className);
        logger.info("│ 方法名: {}", methodName);
        logger.info("│ 参数个数: {}", args.length);
        
        // 记录参数详情
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                logger.info("│ 参数[{}]: {}", i, args[i]);
            }
        }

        // 性能计时
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = null;
        Throwable exception = null;

        try {
            // 执行目标方法
            result = joinPoint.proceed();
            
            // 方法成功执行后的日志
            stopWatch.stop();
            logger.info("│ 方法执行成功");
            logger.info("│ 执行耗时: {} ms", stopWatch.getTotalTimeMillis());
            if (result != null) {
                logger.info("│ 返回值: {}", result);
            }
            
        } catch (Throwable e) {
            // 方法执行异常时的处理
            stopWatch.stop();
            exception = e;
            logger.error("│ 方法执行异常", e);
            logger.error("│ 异常耗时: {} ms", stopWatch.getTotalTimeMillis());
            throw e;
        } finally {
            // 最终日志记录
            logger.info("│ 服务方法执行结束");
            logger.info("└─────────────────────────────────────────────────────────");
            logger.info("");
        }

        return result;
    }
}
