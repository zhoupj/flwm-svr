package com.flwm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flwm.common.VO.UserVO;
import com.flwm.common.config.FlwmConfig;
import com.flwm.common.domain.FMErrorEnum;
import com.flwm.common.domain.FMException;
import com.flwm.common.util.HttpClientUtil;
import com.flwm.common.util.ParamUtil;
import com.flwm.dal.dao.UserDO;
import com.flwm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.MessageFormat;

/**
 * Created by zhoupj on 10/27/18.
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    FlwmConfig flwmConfig;

    private static final String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=wx2e203dc8ee9f36b6&secret=e497e3a5cbbe52061ca0f627cebb63a3&js_code={0}&grant_type=authorization_code";


    /**
     * 小程序登录
     */
    @PostMapping("/login")
    public UserVO login(@RequestParam(value = "code") String code, String name, HttpServletRequest request) {

        String openId = getOpenId(code);
        UserDO user = userService.getUser(openId);
        if (user == null) {
            userService.insertUser(openId, name);
            user = userService.getUser(openId);
        }else{
            userService.updateLastLoginTime(user);
        }

        createSession(request, user);

        return UserVO.convert(user);
    }


    /**
     * web 登录
     */
    @PostMapping("/login2")
    public UserVO login2(@RequestParam(value = "user") String phone, @RequestParam(value = "pwd") String pwd, HttpServletRequest request) {


        if (!ParamUtil.isPhone(phone)) {
            throw new FMException(FMErrorEnum.USER_NOT_REGISTER);
        }
        //openId 做账号,pwd 做密码
        UserDO user = userService.getUserByPhone(phone);
        if (user == null) {
            throw new FMException(FMErrorEnum.USER_NOT_REGISTER);
        }
        if (!pwd.equals(user.getPwd())) {
            throw new FMException(FMErrorEnum.USER_ACCOUNT_WRONG);
        }

        createSession(request, user);

        return UserVO.convert(user);
    }


    private void createSession(HttpServletRequest request, UserDO user) {
        HttpSession session = request.getSession(true);

        session.setAttribute("user", user);
    }


    private String getOpenId(String code) {

        String url = MessageFormat.format(WX_URL, code);
        int retry = 0;
        do {
            String body = HttpClientUtil.get(url);
            JSONObject jo = JSON.parseObject(body);
            if (jo.get("errcode") == null) {
                String openId = (String) jo.get("openid");
                //String sessionKey=(String)jo.get("session_key");
                return openId;
            } else {
                log.error("request wx |" + body);
            }
            retry++;
        } while (retry < 3);

        if ("prod".equals(flwmConfig.getEnv())) {
            throw new FMException(FMErrorEnum.NETWORK_EXCEPTION);
        } else {
            return "test";
        }

    }

    @PostMapping("/quit")
    public boolean logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
        }
        return true;
    }
}
