package com.flwm.web;

import com.flwm.common.cache.UserCache;
import com.flwm.common.config.FlwmConfig;
import com.flwm.common.domain.ErrorCodeEnum;
import com.flwm.common.domain.FMException;
import com.flwm.dal.dao.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by zhoupj on 10/27/18.
 */
@Slf4j
public class SessionInterceptor extends HandlerInterceptorAdapter {


    List<String> filterUrls = Arrays.asList("/login", "/index", "/login2", "/", "/logn");



    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String requestUrl = request.getRequestURI();
        String method = request.getMethod();

        if (filterUrls.contains(requestUrl) || method.equalsIgnoreCase("get")) {
            return true;
        }

//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            log.error("user not login for user:" + requestUrl);
//            throw new FMException(ErrorCodeEnum.USER_NOT_LOGIN);
//            // response.sendRedirect("/login2");
//            // return false;
//        }
//
//        UserDO user = (UserDO) session.getAttribute("user");
//        if (user != null) {
//            UserCache.setUser(user);
//        } else {
//            throw new FMException(ErrorCodeEnum.USER_NOT_LOGIN);
//            //response.sendRedirect("/login2");
//            // return false;
//        }
//
//
//        if (requestUrl.startsWith("/search") && user.getIsMember() == null && user.getIsMember() != 1) {
//
//            throw new FMException(ErrorCodeEnum.USER_ACCOUNT_NOT_MEMBER);
//
//        }

        return true;
    }


}
