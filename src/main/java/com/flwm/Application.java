package com.flwm;

import com.flwm.web.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Application {


    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        /**
         * 预热，防止第一次查询数据时加载太慢
         */
        StartPreLoad startPreLoad =  SpringContextHolder.getBean("startPreLoad");
        startPreLoad.preLoad();

    }
}
