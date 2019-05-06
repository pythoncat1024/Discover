package com.pycat.phone.base;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;
import com.facebook.stetho.Stetho;
import com.pycat.phone.BuildConfig;

public class PhoneApp extends Application {

    private static Application app;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        LogUtils.getLogConfig()
                .configShowBorders(false)
                .configTagPrefix(BuildConfig.APPLICATION_ID);
        app = this;
    }

    public static Application get() {
        return app;
    }
}
