package com.python.cat.accounts;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;

import com.apkfuns.logutils.LogUtils;
import com.python.cat.commonlib.net.cookie.LocalCookieIO;

public class AccountsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.getLogConfig()
                .configShowBorders(false)
                .configTagPrefix(BuildConfig.APPLICATION_ID);
    }


    public static boolean online(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .contains(LocalCookieIO.COOKIE_KEY);
    }
}
