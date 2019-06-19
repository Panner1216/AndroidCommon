package com.panner.common_base.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期辅助工具类
 *
 * @author MoXinA
 */
@SuppressLint("SimpleDateFormat")
public class DateUtils {

    /**
     * 获取日期 MM-dd
     *
     * @return
     */
    public static String getTimeOfMMDD(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        return format.format(new Date(timestamp));
    }

    /**
     * 获取日期 MM-dd
     *
     * @return
     */
    public static String getTimeOfMMDD(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }

        long timestamp = 0;
        try {
            timestamp = Long.parseLong(time);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        return format.format(new Date(timestamp));
    }

    /**
     * 获取日期 MM-dd
     *
     * @return
     */
    public static String getTimeOfHHmm(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(new Date(timestamp));
    }

    /**
     * 获取日期 HH:mm
     *
     * @return
     */
    public static String getTimeOfYYMM(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");
        return format.format(new Date(timestamp));
    }

    /**
     * 获取日期 yyyy.MM.dd
     *
     * @return
     */
    public static String getTimeOfYMDHM(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        long timestamp = Long.parseLong(time) * 1000;
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        return format.format(new Date(timestamp));
    }

    /**
     * 获取日期 yyyy-MM-dd
     *
     * @return
     */
    public static String getTimeOfYMD2(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        long timestamp = Long.parseLong(time) * 1000;
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        return format.format(new Date(timestamp));
    }

    /**
     * 获取日期 yyyy-MM-dd
     *
     * @return
     */
    public static String getTimeOfYMD(String time) {
        if (TextUtils.isEmpty(time) || time.equals("0")) {
            return "暂无资料";
        }
        long timestamp = Long.parseLong(time) * 1000;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date(timestamp));
    }

    /**
     * 获取日期 yyyy-MM
     *
     * @return
     */
    public static String getTimeOfYM(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        long timestamp = Long.parseLong(time) * 1000;
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        return format.format(new Date(timestamp));
    }

    /**
     * 获取日期 yyyy-MM-DD
     *
     * @return
     */
    public static String getTimeOfYMD3(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        long timestamp = Long.parseLong(time) * 1000;
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(new Date(timestamp));
    }

    /**
     * 获取与当前年份差值
     */
    public static int getGapValueOfYear(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        int currentYear = Integer.parseInt(format.format(System
                .currentTimeMillis()));
        int timeYear = Integer.parseInt(format.format(timestamp));
        return currentYear - timeYear;
    }

    /**
     * 获取与当前月份差值
     */
    public static int getGapValueOfMonth(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        int currentMonth = Integer.parseInt(format.format(System
                .currentTimeMillis()));
        int timeMonth = Integer.parseInt(format.format(timestamp));
        return currentMonth - timeMonth;
    }

    /**
     * 获取时间年份
     */
    public static String getYear(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(new Date(timestamp));
    }

    /**
     * 获取时间月份
     */
    public static String getMonth(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        return format.format(new Date(timestamp));
    }

    /**
     * 获取时间差
     *
     * @param time
     * @return
     */
    public static String getGapValueOfTime(long time) {
        Date date = new Date(System.currentTimeMillis());
        long currentTime = date.getTime();
        long diff = currentTime / 1000 - time;
        if (diff < 60 * 60 - 1) {
            return diff / 60 + "分钟前";
        } else if (diff < 24 * 60 * 60 - 1) {
            return diff / (60 * 60) + "小时前";
        } else if (diff < 30 * 24 * 60 * 60 - 1) {
            return diff / (24 * 60 * 60) + "天前";
        } else {
            return getTimeOfYMD(String.valueOf(time));
        }
    }
}
