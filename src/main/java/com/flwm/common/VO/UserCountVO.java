package com.flwm.common.VO;

import lombok.Data;

@Data
public class UserCountVO {

    /**
     * 用户数量
     */
    private long uc;

    /**
     * 会员数量
     */
    private long mc;


    public UserCountVO(long uc,long mc){
        this.uc=uc;
        this.mc=mc;
    }


}
