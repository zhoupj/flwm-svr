package com.flwm.controller;

import com.flwm.common.cache.UserCache;
import com.flwm.dal.dao.ActivityDO;
import com.flwm.dal.dao.UserDO;
import com.flwm.dal.mapper.ActivityDOMapper;
import com.flwm.service.ActivityService;
import com.flwm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhoupj on 10/28/18.
 */
@RestController
@RequestMapping(value = "/user",method = RequestMethod.POST)
public class UserController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;

    @PostMapping("/activity")
    public List<ActivityDO> getMemberActivity() {
        return activityService.getAll();
    }

    @PostMapping("/buy_member")
    public boolean buyMember(@RequestParam(value = "actId") Integer actId){

        UserDO user= UserCache.getUser();
        userService.buyMember(user.getId(),actId);
        return true;
    }

    @PostMapping("/suggest")
    public boolean buyMember(@RequestParam(value = "txt") String txt){
        UserDO user= UserCache.getUser();
        userService.suggest(user.getId(),txt);
        return true;
    }



}
