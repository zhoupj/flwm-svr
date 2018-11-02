package com.flwm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flwm.common.config.FlwmConfig;
import com.flwm.common.domain.ErrorCodeEnum;
import com.flwm.common.domain.FMException;
import com.flwm.common.util.HttpClientUtil;
import com.flwm.dal.dao.UserDO;
import com.flwm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/login")
    public UserDO login(@RequestParam(value = "code") String code, String name, HttpServletRequest request) {

        String openId=getOpenId(code);
        UserDO user=userService.getUser(openId);
        if(user==null){
            userService.saveUser(openId,name);
            user=userService.getUser(openId);
        }else{
            userService.flushUserInfo(user.getId());
        }
        HttpSession session = request.getSession(true);
        log.info("session id:"+ session.getId());
        session.setAttribute("user",user);
        return user;
    }


    private String getOpenId(String code){

        String url= MessageFormat.format(WX_URL,code);

        System.out.println("code:"+code);
        int retry=0;
        do{
            String body= HttpClientUtil.get(url);
            JSONObject jo = JSON.parseObject(body);
            if(jo.get("errcode")==null){
                String openId=(String)jo.get("openid");
                //String sessionKey=(String)jo.get("session_key");
                return openId;
            }else{
                log.error("request wx |"+body);
            }
            retry++;
        }while(retry<3);

        if("prod".equals(flwmConfig.getEnv())){
            throw new FMException(ErrorCodeEnum.NETWORK_EXCEPTION);
        }else{
            return "test";
        }

    }

    @RequestMapping("/quit")
    public boolean logout( HttpServletRequest request){

        HttpSession session = request.getSession(true);
        session.removeAttribute("user");
        return true;
    }
}
