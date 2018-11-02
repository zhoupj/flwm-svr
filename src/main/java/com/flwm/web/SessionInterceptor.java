package com.flwm.web;

import com.flwm.common.cache.UserCache;
import com.flwm.common.config.FlwmConfig;
import com.flwm.common.domain.ErrorCodeEnum;
import com.flwm.common.domain.FMException;
import com.flwm.dal.dao.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhoupj on 10/27/18.
 */
@Slf4j
public class SessionInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String requestUrl = request.getRequestURI();

        System.out.println(requestUrl);

        if (requestUrl.equals("/login")
                || requestUrl.equals("/index.html")
                || requestUrl.equals("/index")
                || requestUrl.equals("/")) {
            return true;
        }

//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            log.error("user not login for uer:" + requestUrl);
//            throw new FMException(ErrorCodeEnum.USER_NOT_LOGIN);
//        }
//        UserDO user = (UserDO) session.getAttribute("user");
//        if (user != null) {
//            UserCache.setUser(user);
//        }
        return true;
    }


}
