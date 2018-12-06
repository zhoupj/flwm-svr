package com.flwm.common.auth;

import lombok.Getter;

public enum MemberLevelEnum {
    USER(0),//普通用户
    ONE_LEVEL(1),//赠送得会员
    SUPER(2);//充值会员

    @Getter
    private int level;

    MemberLevelEnum(int level) {
        this.level = level;
    }

}
