package com.python.cat.commonlib.net.cookie;

import android.content.Context;

import androidx.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * https://www.cnblogs.com/zhujiabin/p/6207913.html
 * This Interceptor add all received Cookies to the app DefaultPreferences.
 * Your implementation on how to save the Cookies on the Preferences MAY VARY.
 * <p>
 * Created by tsuharesu on 4/1/15.
 */
public class ReceivedCookies implements Interceptor {

    private Context mContext;

    public ReceivedCookies(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        LogUtils.e(Thread.currentThread().getName());
        Response originalResponse = chain.proceed(chain.request());
//        LogUtils.e(originalResponse.headers());
//        LogUtils.e(originalResponse.body());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>(originalResponse.headers("Set-Cookie"));
            LocalCookieIO.saveCookie(mContext, cookies);
            LogUtils.w(cookies);
        }
        return originalResponse;
    }
}