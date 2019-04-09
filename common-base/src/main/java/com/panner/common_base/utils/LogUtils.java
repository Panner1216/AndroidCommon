package com.panner.common_base.utils;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * log处理类
 *
 * @author Panner
 * @version 2019-04-07-22:42
 */
public class LogUtils {
    private static final String TAG = LogUtils.class.getSimpleName();
    public static boolean DEBUG = true;

    /**
     * LOG INIT,需要在application中初始化
     *
     * @param tag
     * @param isDebug 是否开启日志
     */
    public static void init(String tag, boolean isDebug) {
        Logger.addLogAdapter(new AndroidLogAdapter());
        DEBUG = isDebug;
    }

    /**
     * LOG INIT 默认打开日志
     *
     * @param tag
     */
    public static void init(String tag) {
        init(tag, true);
    }

    /**
     * LOG INIT 默认使用项目名打开日志
     *
     * @param context {@link Context}
     */
    public static void init(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            init(context.getResources().getString(labelRes));
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(TAG, e);
        }
    }

    /**
     * 是否打开日志
     *
     * @return true开，false关
     */
    public static boolean isDebugMode() {
        return DEBUG;
    }


    /**
     * JSON
     *
     * @param TAG     LOG TAG
     * @param message json string
     */
    public static void json(String TAG, String message) {
        Logger.t(TAG).json(message);
    }

    /**
     * XML
     *
     * @param TAG     LOG TAG
     * @param message XML string
     */
    public static void xml(String TAG, String message) {
        Logger.t(TAG).xml(message);
    }

    /**
     * list
     *
     * @param TAG  log tag
     * @param list list
     */
    public static void listD(String TAG, List list) {
        Logger.t(TAG).d(list);
    }

    /**
     * map
     *
     * @param TAG log tag
     * @param map map
     */
    public static void mapD(String TAG, Map map) {
        Logger.t(TAG).d(map);
    }

    /**
     * set
     *
     * @param TAG log tag
     * @param set set
     */
    public static void setD(String TAG, Set set) {
        Logger.t(TAG).d(set);
    }

    /**
     * array
     *
     * @param TAG   log tag
     * @param array array
     */
    public static void arrayD(String TAG, Array array) {
        Logger.t(TAG).d(array);
    }

    /**
     * INFO
     *
     * @param TAG       LOG TAG
     * @param message   提示模板 %s
     * @param parameter 参数
     */
    public static void i(String TAG, String message, Object... parameter) {
        Logger.t(TAG).i(message, parameter);
    }

    /**
     * DEBUG
     *
     * @param TAG       LOG TAG
     * @param message   提示模板 %s
     * @param parameter 参数
     */
    public static void d(String TAG, String message, Object... parameter) {
        Logger.t(TAG).d(message, parameter);
    }

    /**
     * WARN
     *
     * @param TAG       LOG TAG
     * @param message   提示模板 %s
     * @param parameter 参数
     */
    public static void w(String TAG, String message, Object... parameter) {
        Logger.t(TAG).w(message, parameter);
    }

    /**
     * ERROR
     *
     * @param TAG       LOG TAG
     * @param e         {@link Exception}
     * @param message   错误信息 %s站位
     * @param parameter 错误信息内容
     */
    public static void e(String TAG, @NonNull Throwable e, String message, Object... parameter) {
        Logger.t(TAG).e(e, message, parameter);
    }

    /**
     * ERROR
     *
     * @param TAG       LOG TAG
     * @param message   错误信息 %s站位
     * @param parameter 错误信息内容
     */
    public static void e(String TAG, String message, Object... parameter) {
        Logger.t(TAG).e(message, parameter);
    }

    /**
     * ERROR
     *
     * @param TAG LOG TAG
     * @param e   {@link Throwable}
     */
    public static void e(String TAG, @NonNull Throwable e) {
        Logger.t(TAG).e(e, e.getMessage());
    }
}
