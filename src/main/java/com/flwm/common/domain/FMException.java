package com.flwm.common.domain;

import lombok.Data;

/**
 * Created by zhoupj on 10/25/18.
 */
@Data
public class FMException extends RuntimeException {

    private ErrorCodeEnum err;

    public  FMException(ErrorCodeEnum err){
        super();
        this.err=err;

    }

    public  FMException(String msg){
        super(msg);
        this.err=ErrorCodeEnum.SYS_EXCEPTION;

    }
}
