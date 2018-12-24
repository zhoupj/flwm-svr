package com.flwm.common.domain;

import com.flwm.dal.dao.BasicDO;
import lombok.Data;

@Data
public class BasicVO {

    private String code;

    private String name;


    public BasicVO(BasicDO bd){
        this.code=bd.getCode();
        this.name=bd.getName();
    }
}
