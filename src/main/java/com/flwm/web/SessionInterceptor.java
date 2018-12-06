package com.flwm.web;

import com.flwm.common.cache.UserCache;
import com.flwm.common.domain.FMErrorEnum;
import com.flwm.common.domain.FMException;
import com.flwm.dal.dao.UserDO;
import com.flwm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhoupj on 10/27/18.
 */
@Slf4j
public class SessionInterceptor extends HandlerInterceptorAdapter {


    List<String> filterUrls = Arrays.asList("/login", "/index", "/login2", "/", "/art/list", "/art/detail");


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String requestUrl = request.getRequestURI();
        String method = request.getMethod();


        /**
         * 链路追踪
         */
        MDC.put("tracerId", UUID.randomUUID().toString());

        if (filterUrls.contains(requestUrl) || method.equalsIgnoreCase("get")) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session == null) {
            log.error("user not login for user:" + requestUrl);
            throw new FMException(FMErrorEnum.USER_NOT_LOGIN);
            // response.sendRedirect("/login2");
            // return false;
        }

        UserDO user = (UserDO) session.getAttribute("user");
        if (user != null) {
            UserCache.setUser(((UserService) SpringContextHolder.getBean("userService")).getUser(user.getId()));
        } else {
            throw new FMException(FMErrorEnum.USER_NOT_LOGIN);
        }

        return true;
    }


    /**
     * This implementation is empty.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {

        MDC.remove("tracerId");
    }


}
