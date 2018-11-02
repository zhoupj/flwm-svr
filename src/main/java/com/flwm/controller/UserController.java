package com.flwm.controller;

import com.flwm.common.cache.UserCache;
import com.flwm.dal.dao.ActivityDO;
import com.flwm.dal.dao.UserDO;
import com.flwm.dal.mapper.ActivityDOMapper;
import com.flwm.service.ActivityService;
import com.flwm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhoupj on 10/28/18.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;

    @RequestMapping("/activity")
    public List<ActivityDO> getMemberActivity() {
        return activityService.getAll();
    }

    @RequestMapping("/buy_member")
    public boolean buyMember(@RequestParam(value = "actId") Integer actId){

        UserDO user= UserCache.getUser();
        userService.buyMember(user.getId(),actId);
        return true;
    }

    @RequestMapping("/suggest")
    public boolean buyMember(@RequestParam(value = "txt") String txt){
        UserDO user= UserCache.getUser();
        userService.suggest(user.getId(),txt);
        return true;
    }



}
