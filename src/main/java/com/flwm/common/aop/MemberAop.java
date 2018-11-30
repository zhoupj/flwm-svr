package com.flwm.common.aop;

import com.flwm.common.annotation.AuthMember;
import com.flwm.common.cache.UserCache;
import com.flwm.common.domain.FMErrorEnum;
import com.flwm.common.domain.FMException;
import com.flwm.dal.dao.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Order(0)
@Component
@Slf4j
public class MemberAop {

    @Pointcut("execution(* com.flwm.controller..*.*(..))")
    public void controller() {
    }

    /**
     * around:ProceedingJoinPoint
     * @param pjp
     */
    @Before("controller()")
    public void around(JoinPoint pjp) {

        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method targetMethod = methodSignature.getMethod();

        Annotation ana = targetMethod.getAnnotation(AuthMember.class);

        if (ana != null) {

            UserDO userDO = UserCache.getUser();

            if (userDO == null || userDO.getIsMember() != 1) {
                throw new FMException(FMErrorEnum.USER_ACCOUNT_NOT_MEMBER);
            }

        }
    }

}
