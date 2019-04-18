package com.pycat.application.base

import android.app.Application
import com.apkfuns.logutils.LogUtils
import com.pycat.application.BuildConfig

class KotlinApp : Application() {

    override fun onCreate() {
        super.onCreate()

        LogUtils.getLogConfig()
                .configTagPrefix(BuildConfig.APPLICATION_ID);
    }

    override fun onTerminate() {
        super.onTerminate()
        LogUtils.e("onTerminate")
    }
}