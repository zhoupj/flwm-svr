package com.flwm.controller;

import com.flwm.common.cache.UserCache;
import com.flwm.common.domain.FMErrorEnum;
import com.flwm.common.domain.FMException;
import com.flwm.common.util.DateUtil;
import com.flwm.dal.dao.ActivityDO;
import com.flwm.dal.dao.UserDO;
import com.flwm.dal.mapper.ActivityDOMapper;
import com.flwm.service.ActivityService;
import com.flwm.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhoupj on 10/28/18.
 */
@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
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
    public boolean buyMember(@RequestParam(value = "actId") Integer actId) {

        UserDO user = UserCache.getUser();

        if (StringUtils.isBlank(user.getPhone())) {
            throw new FMException(FMErrorEnum.MEMBER_BUY_BIND_PHONE);
        }
        userService.buyMember(user.getId(), actId);
        return true;
    }

    @PostMapping("/suggest")
    public boolean buyMember(@RequestParam(value = "txt") String txt) {
        UserDO user = UserCache.getUser();
        userService.suggest(user.getId(), txt);
        return true;
    }

    @PostMapping("/ds")
    public List<String> getUseDateList() {

        UserDO user = UserCache.getUser();
        if (user.getIsMember() == 1) {
            return DateUtil.getDateList(0, 30);
        } else {
            return DateUtil.getDateList(-1, 3);
        }

    }

    @PostMapping("/ph")
    public boolean bindPhone(@RequestParam(value = "phone") String phone) {
        UserDO user = UserCache.getUser();
        userService.updateUserPhone(UserCache.getUserId(), phone);
        return true;

    }


}
