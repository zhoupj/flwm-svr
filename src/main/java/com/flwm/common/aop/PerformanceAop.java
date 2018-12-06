package com.flwm.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j(topic = "cost")
public class PerformanceAop {


    @Pointcut("execution(* com.flwm.service..*.*(..))")
    public void service() {
    }

    /**
     * around:ProceedingJoinPoint
     *
     * @param pjp
     */
    @Around("service()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        long start = System.currentTimeMillis();

        try {
          return  pjp.proceed();

        } finally {

            long end = System.currentTimeMillis();
            long cost = end - start;

            if (cost >= 1000) {
                MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
                Method targetMethod = methodSignature.getMethod();
                log.info(targetMethod.getDeclaringClass().getSimpleName() + "|" + targetMethod.getName() + "|" + cost);
            }
        }

    }
}
