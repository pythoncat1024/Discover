package com.python.cat.commonlib.net.cookie;

import android.content.Context;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * https://www.cnblogs.com/zhujiabin/p/6207913.html
 * This interceptor put all the Cookies in Preferences in the Request.
 * Your implementation on how to get the Preferences MAY VARY.
 * <p>
 * Created by tsuharesu on 4/1/15.
 */
public class AddCookies implements Interceptor {

    private Context mContext;

    public AddCookies(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        LogUtils.e(Thread.currentThread().getName());
        Request.Builder builder = chain.request().newBuilder();
        Set<String> stringSet = PreferenceManager.getDefaultSharedPreferences(mContext)
                .getStringSet(HttpClient.COOKIE_KEY, new HashSet<>());
        if (stringSet != null) {
            for (String cookie : stringSet) {
                builder.addHeader("Cookie", cookie);
                LogUtils.i("Adding Header: " + cookie);
                // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
            }
        }
        LogUtils.i(stringSet);
        return chain.proceed(builder.build());
    }
}