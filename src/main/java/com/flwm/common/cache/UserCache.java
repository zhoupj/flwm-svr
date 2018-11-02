package com.flwm.common.cache;

import com.flwm.common.domain.ErrorCodeEnum;
import com.flwm.common.domain.FMException;
import com.flwm.dal.dao.UserDO;

/**
 * Created by zhoupj on 10/27/18.
 */
public class UserCache {

   private static ThreadLocal userLocal=new ThreadLocal();


    public static void setUser(UserDO user){
        userLocal.set(user);

    }

    public static UserDO getUser(){
        return (UserDO)userLocal.get();
    }


    public static Integer getUserId(){
        UserDO user= (UserDO)userLocal.get();
        if(user==null){
            throw new FMException(ErrorCodeEnum.USER_NOT_LOGIN);
        }
        return user.getId();
    }

}
