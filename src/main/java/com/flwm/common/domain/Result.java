package com.flwm.common.domain;

import lombok.Data;

/**
 * Created by zhoupj on 10/25/18.
 */
@Data
public class Result {

    private boolean success;
    private String code;
    private String msg;
    private Object data;
    private long total;

    public static Result succ(Object data){
        Result rst=new Result();
        rst.success=true;
        rst.data=data;
        rst.code=ErrorCodeEnum.SUCCESS.getCode();
        rst.msg=ErrorCodeEnum.SUCCESS.getMsg();
        return rst;
    }

    public static Result succ(Object data,long total){
        Result rst=succ(data);
        rst.total=total;
        return rst;
    }

    public static Result fail(ErrorCodeEnum err){
        Result rst=new Result();
        rst.success=false;
        rst.data=null;
        rst.code=err.getCode();
        rst.msg=err.getMsg();
        return rst;
    }


}
