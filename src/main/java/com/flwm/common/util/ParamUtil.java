package com.flwm.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhoupj on 11/8/18.
 */
public class ParamUtil {


    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }

    public static boolean isEmail(String string) {
        if (string == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }





    public static void main(String [] args){
        System.out.println(ParamUtil.isPhone("123"));
        System.out.println(ParamUtil.isPhone("18457177669"));
        System.out.println(ParamUtil.isPhone("zhoupj"));
        System.out.println(ParamUtil.isEmail("zhoupj@154.com"));
    }



}
