package com.python.cat.splash;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;

public class SplashApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.getLogConfig()
                .configTagPrefix(BuildConfig.APPLICATION_ID)
                .configShowBorders(false);
    }
}
