package com.itheima.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换相关工具类
 */
public class DateUtils {
    /**
     * Date转换为String
     */
    public static String date2String(Date date,String pattern){
        //初始化日期格式化对象
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        //将日期转换为字符串
        String dateStr = dateFormat.format(date);

        return dateStr;
    }

    /**
     * 将字符串转换为日期
     */
    public static Date string2Date(String dateStr,String pattern){
        //初始化日期格式化对象
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        //将字符串转换为日期
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
