package com.pycat.phone.base;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;
import com.pycat.phone.BuildConfig;

public class PhoneApp extends Application {

    private static Application app;

    @Override
    public void onCreate() {
        super.onCreate();

        LogUtils.getLogConfig()
                .configShowBorders(false)
                .configTagPrefix(BuildConfig.APPLICATION_ID);
        app = this;
    }

    public static Application get() {
        return app;
    }
}
