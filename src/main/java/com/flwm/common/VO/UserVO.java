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

    private String name;

    private String phone;

    private String email;


    private Integer isMember;

    private Date memberDeadline;

    private Date firstLoginTime;

    private Date lastLoginTime;



    public static UserVO convert(UserDO userDO){
       return BeanUtil.convert(userDO,UserVO.class);
    }
}
