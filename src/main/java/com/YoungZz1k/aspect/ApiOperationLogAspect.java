package com.YoungZz1k.aspect;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 日志切面
 */
@Aspect
@Component
@Slf4j
public class ApiOperationLogAspect {

    // 以ApiOperationLog为切入点 标注了这个注解的方法会执行环绕中的代码
    @Pointcut("@annotation(com.YoungZz1k.aspect.ApiOperationLog)")
    public void apiOperationLog() {
    }

    ;

    /**
     * 环绕
     *
     * @param joinPoint
     * @return
     */
    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // 开始时间
            long startTime = System.currentTimeMillis();

            MDC.put("traceId", UUID.randomUUID().toString().replace("-", ""));

            // 请求的类名
            String className = joinPoint.getTarget().getClass().getSimpleName();
            // 请求的方法名
            String methodName = joinPoint.getSignature().getName();

            // 请求的入参
            Object[] args = joinPoint.getArgs();
            // 入参转Json字符串
            String argsJsonStr = Arrays.stream(args).map(JSONUtil::toJsonStr).collect(Collectors.joining(","));

            // 功能描述信息
            String description = getApiOperationLogDescription(joinPoint);

            // 打印请求相关参数
            log.info("======== 请求开始: [{}], 入参: {}, 请求类: {}, 请求方法: {} ============= ",
                    description, argsJsonStr, className, methodName);

            // 执行切点方法
            Object result = joinPoint.proceed();

            long endTime = (System.currentTimeMillis() - startTime);

            // 打印出参等相关信息
            log.info("======== 请求结束: [{}], 耗时: {}ms, 出参: {} ============= ",
                    description, endTime, JSONUtil.toJsonStr(result));

            return result;

        } finally {
            MDC.clear();
        }
    }


    private String getApiOperationLogDescription(ProceedingJoinPoint joinPoint) {

        // 获取MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // 获取被调用的方法
        Method method = signature.getMethod();

        // 获取方法上的注解
        ApiOperationLog annotation = method.getAnnotation(ApiOperationLog.class);

        // 获取description
        return annotation.description();
    }
}
