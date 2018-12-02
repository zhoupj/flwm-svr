package com.flwm.common.annotation;

import com.flwm.common.auth.MemberLevelEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthMember {

    MemberLevelEnum value() default MemberLevelEnum.ONE_LEVEL;

}
