package com.flwm.web;


import com.alibaba.fastjson.JSON;
import com.flwm.dal.dao.UserDO;
import com.flwm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCache(){
       UserDO userDO= userService.getUser(1);
       userService.updateUserPhone(1,"3333333");
       userDO=userService.getUser(1);

       System.out.println(JSON.toJSONString(userDO));


    }
}
