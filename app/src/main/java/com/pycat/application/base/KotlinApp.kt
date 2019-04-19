package com.pycat.application.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.apkfuns.logutils.LogUtils
import com.pycat.application.BuildConfig

class KotlinApp : MultiDexApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()

        LogUtils.getLogConfig()
                .configTagPrefix(BuildConfig.APPLICATION_ID);
        LogUtils.e("onCreate")
    }

    override fun onTerminate() {
        super.onTerminate()
        LogUtils.e("onTerminate")
    }
}