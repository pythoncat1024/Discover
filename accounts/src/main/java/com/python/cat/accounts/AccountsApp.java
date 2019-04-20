package com.python.cat.accounts;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;

public class AccountsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.getLogConfig()
                .configShowBorders(false)
                .configTagPrefix(BuildConfig.APPLICATION_ID);
    }

}
