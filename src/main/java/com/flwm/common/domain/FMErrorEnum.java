package com.flwm.common.domain;

import lombok.Getter;

/**
 * Created by zhoupj on 10/25/18.
 */

public enum FMErrorEnum {


    SUCCESS("1001", "OK"),

    SYS_EXCEPTION("1002", "系统异常"),
    REQUEST_EXCEPTION("1003", "请求异常"),
    NETWORK_EXCEPTION("1004", "WX网络异常"),
    PARAM_EXCEPTION("1005", "参数不对"),
    URL_EXCEPTION("1006", "路径不对"),
    INVALID_QUERY("1007","非法查询"),

    USER_NOT_LOGIN("2003", "未登录"),
    USER_NOT_REGISTER("2004", " 未注册"),
    USER_ACCOUNT_WRONG("2005", " 账号或者密码错误"),
    USER_ACCOUNT_FORMAT_WRONG("2006", " 账号必须为手机号"),
    USER_ACCOUNT_NOT_MEMBER("2007", "非会员不能查看"),


    MEMBER_BUY_FORBIDDEN("3001", "会员未到期不能购买"),
    MEMBER_BUY_BIND_PHONE("3002", "会员需要先绑定手机号"),


    SEL_ADD_TOO_MANY("4001", "每个类型个数上限是100"),
    SEL_ADD_EXIST("4002", "已经存在"),

    SEARCH_TOO_MANY("5001","查询条件太粗糙了，请缩小查询条件"),

    CODE_NOT_EXIST("6001", "输入的代码或名称不存在"),



    ;


    @Getter
    private String code;
    @Getter
    private String msg;


    FMErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
