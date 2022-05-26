package com.movie.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class DataTimeUtil {

    public static String getNowTimeString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(System.currentTimeMillis());
    }

    public static long parseTimeStamp(String s) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long t = 0;
        try {
            t = df.parse(s).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static boolean isAfterNow(String time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        long t = 0;
        try {
            t = df.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return t > System.currentTimeMillis();
    }

}
