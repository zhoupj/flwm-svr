package com.flwm.common.auth;

import lombok.Getter;

public enum MemberLevelEnum {

    ONE_LEVEL(1),//赠送会员，可查看详情
    SUPER(2);//all

    @Getter
    private int level;
    MemberLevelEnum(int level){
        this.level=level;
    }

}
