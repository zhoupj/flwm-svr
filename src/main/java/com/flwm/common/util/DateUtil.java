package com.flwm.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhoupj on 10/27/18.
 */
public class DateUtil {


    public static Date getDayByCode(String code) {
        Calendar cal = Calendar.getInstance();//使
        switch (code) {
            case "week_v":
                cal.add(Calendar.DAY_OF_MONTH, 7);
                break;
            case "month_v":
                cal.add(Calendar.DAY_OF_MONTH, 30);
                break;
            case "season_v":
                cal.add(Calendar.DAY_OF_MONTH, 121);
                break;
            case "year_v":
                cal.add(Calendar.DAY_OF_MONTH, 365);
                break;
            default:
                throw new RuntimeException("error code for member activity");

        }
        Date target = cal.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(target);
        String newTarget = s + " 23:59:59";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(newTarget);
        } catch (Exception e) {
            e.printStackTrace();
            return target;
        }


    }


    public static String getReportDate(String dt) {

        if(dt==null || "".equals(dt)){
            return null;
        }

        String prefix = dt.substring(0, 5);
        String dtSuffix = dt.substring(5);
        if (dtSuffix.compareTo("01-01") >= 0 && dtSuffix.compareTo("03-31") <= 0) {
            return (Integer.valueOf(dt.substring(0, 4))-1) + "-12-31";
        }
        if (dtSuffix.compareTo("03-31") > 0 && dtSuffix.compareTo("06-30") <= 0) {
            return prefix + "03-31";
        }
        if (dtSuffix.compareTo("06-30") > 0 && dtSuffix.compareTo("09-30") <= 0) {
            return prefix + "06-30";
        }
        if (dtSuffix.compareTo("09-30") > 0 && dtSuffix.compareTo("12-31") <= 0) {
            return prefix + "09-30";
        }
        return dt;
    }


    public static String getShortFormat(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return  dateString;
    }

    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = df.format(getDayByCode("week_v"));

        System.out.println(s);
        System.out.println(getReportDate("2018-09-23"));
        System.out.println(getReportDate("2018-12-23"));
        System.out.println(getReportDate("2018-01-08"));
        System.out.println(getShortFormat(new Date()));
    }

}
