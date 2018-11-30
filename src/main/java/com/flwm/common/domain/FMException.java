package com.flwm.common.domain;

import lombok.Data;

/**
 * Created by zhoupj on 10/25/18.
 */
@Data
public class FMException extends RuntimeException {

    private FMErrorEnum err;

    public  FMException(FMErrorEnum err){
        super();
        this.err=err;

    }

    public  FMException(String msg){
        super(msg);
        this.err= FMErrorEnum.SYS_EXCEPTION;

    }
}
