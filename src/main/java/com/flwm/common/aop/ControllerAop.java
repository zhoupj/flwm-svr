package com.flwm.common.aop;

import com.flwm.common.annotation.AuthMember;
import com.flwm.common.annotation.QueryLimit;
import com.flwm.common.auth.MemberLevelEnum;
import com.flwm.common.cache.QueryLimitCache;
import com.flwm.common.cache.UserCache;
import com.flwm.common.domain.FMErrorEnum;
import com.flwm.common.domain.FMException;
import com.flwm.common.util.DateUtil;
import com.flwm.dal.dao.UserDO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.management.Query;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Order(0)
@Component
@Slf4j
public class ControllerAop {


    @Pointcut("execution(* com.flwm.controller..*.*(..))")
    public void controller() {
    }

    /**
     * around:ProceedingJoinPoint
     *
     * @param pjp
     */
    @Before("controller()")
    public void around(JoinPoint pjp) {

        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method targetMethod = methodSignature.getMethod();
        checkMember(targetMethod);
        checkQueryLimit(targetMethod);
    }


    private void checkMember(Method targetMethod) {

        Annotation ana = targetMethod.getAnnotation(AuthMember.class);
        if (ana != null) {
            UserDO userDO = UserCache.getUser();
            if (userDO == null || userDO.getIsMember() == MemberLevelEnum.USER.getLevel()) {
                throw new FMException(FMErrorEnum.USER_ACCOUNT_NOT_MEMBER);
            }

        }
    }


    private void checkQueryLimit(Method targetMethod) {

        Annotation ana = targetMethod.getAnnotation(QueryLimit.class);

        if (ana != null) {
            UserDO userDO = UserCache.getUser();
            QueryLimitCache.checkIfCanSearch(userDO, ((QueryLimit) ana).value());
        }
    }

}
