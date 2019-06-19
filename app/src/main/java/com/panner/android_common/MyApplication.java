package com.panner.android_common;

import android.app.Application;

import com.panner.common_base.utils.LogUtils;

/**
 * @author Panner
 * @version 2019-04-07-22:36
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.init(this);

    }
}
