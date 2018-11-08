package com.flwm.common.VO;

import com.flwm.common.util.BeanUtil;
import com.flwm.dal.dao.UserDO;
import lombok.Data;

import java.util.Date;

/**
 * Created by zhoupj on 11/8/18.
 */
@Data
public class UserVO {

    private String openId;

    private String name;

    private Integer isMember;

    private Date memberDeadline;

    private Date lastLoginTime;

    private Integer loginDays;

    private Date thisLoginTime;


    public static UserVO convert(UserDO userDO){
       return BeanUtil.convert(userDO,UserVO.class);
    }
}
