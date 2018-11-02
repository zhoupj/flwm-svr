package com.flwm.common.domain;

import lombok.Getter;

/**
 * Created by zhoupj on 10/25/18.
 */

public enum  ErrorCodeEnum {


     SUCCESS("1001","OK"),

     SYS_EXCEPTION("1002","系统异常"),
     REQUEST_EXCEPTION("1003","请求异常"),
     NETWORK_EXCEPTION("1004","WX网络异常"),
     PARAM_EXCEPTION("1005","参数不对"),
     URL_EXCEPTION("1006","路径不对"),

     USER_NOT_LOGIN("1003","未登录"),


     MEMBER_BUY_FORBIDDEN("3001","会员未到期不能购买"),
     SEL_ADD_TOOMANAY("4001","每个类型个数上限是100"),
    SEL_ADD_EXIST("4002","已经存在"),

    ;


    @Getter
    private String code;
    @Getter
    private String msg;


    ErrorCodeEnum(String  code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
