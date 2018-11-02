package com.flwm.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by zhoupj on 10/27/18.
 */
@Slf4j(topic = "digest")
public class LogUtil {


    private static String SEP="|";

    public static String BUY="buy";
    public static String QUERY="query";
    public static String LOGIN="login";
    public static String SUGGEST="suggest";


    public static void log(String action,Integer userId,Object ...args){

        String l=action+SEP+userId;
        if(args!=null){
            for(Object arg:args){
                l=l+SEP+arg;
            }
        }
        log.info(l);

    }


}
