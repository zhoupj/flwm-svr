package com.flwm.common.domain;

import lombok.Data;

/**
 * Created by zhoupj on 10/29/18.
 */
@Data
public class PageRequest {

    int pageNo=1;
    int pageSize=10;

    public int getOffset(){
        if(pageNo<1){
            pageNo=1;
        }
        if(pageSize<=0){
            pageSize=20;
        }
        return (pageNo-1)*pageSize;
    }
}
