package com.flwm.common.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhoupj on 10/31/18.
 */
@Data
public class UserShareRequest {

    //
    private Integer userId;

    //
    private String shareCode;

    //1 观察 2候选 3 持有4 淘汰
    private Integer sGroup;


   public UserShareRequest (Integer userId,String shareCode,Integer group){
       this.userId=userId;
       this.shareCode=shareCode;
       this.sGroup=group;
   }

}
