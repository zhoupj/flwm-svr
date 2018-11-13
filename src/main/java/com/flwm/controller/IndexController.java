package com.flwm.controller;

import com.flwm.common.config.FlwmConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by zhoupj on 10/31/18.
 */
@Controller
public class IndexController {

    @Autowired
    FlwmConfig flwmConfig;

    @RequestMapping(value = "/**")
    public String index(Model mv, HttpServletRequest request) {

        //System.out.println("拦截啦拦截啦拦截啦拦截啦拦截啦拦截啦:" + flwmConfig.getDomainName() + "," + flwmConfig.getEnv()+","+request.getRequestURI());
        mv.addAttribute("domain_name", flwmConfig.getDomainName());

        if (flwmConfig.getEnv().equals("dev")) {
            return "index";
        } else {
            return "index";
        }
    }

//    @PostMapping(value = "/logn")
//    public String login(Model mv, HttpServletRequest request) {
//
//        //System.out.println("拦截啦拦截啦拦截啦拦截啦拦截啦拦截啦:" + flwmConfig.getDomainName() + "," + flwmConfig.getEnv()+","+request.getRequestURI());
//        mv.addAttribute("domain_name", flwmConfig.getDomainName());
//
//        if (flwmConfig.getEnv().equals("dev")) {
//            return "index";
//        } else {
//            return "index";
//        }
//    }

}
