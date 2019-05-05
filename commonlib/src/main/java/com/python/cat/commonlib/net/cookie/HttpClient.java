package com.python.cat.commonlib.net.cookie;

import android.content.Context;

import androidx.annotation.NonNull;

import okhttp3.OkHttpClient;

/**
 * https://www.cnblogs.com/zhujiabin/p/6207913.html
 */
public class HttpClient {

    public static final String COOKIE_KEY = "store_cookie_in_sp";

    /**
     * a http client contains cookie store and use
     */
    public static OkHttpClient cookieClient(@NonNull Context context) {
        /**
         * Somewhere you create a new OkHttpClient and use it on all your requests.
         */
        return new OkHttpClient.Builder()
                .addInterceptor(new AddCookies(context))
                .addInterceptor(new ReceivedCookies(context))
                .build();
    }
    /**
     * a http client contains cookie store and use
     */
    public static OkHttpClient storeCookieClient(@NonNull Context context) {
        /**
         * Somewhere you create a new OkHttpClient and use it on all your requests.
         */
        return new OkHttpClient.Builder()
                .addInterceptor(new AddCookies(context))
                .addInterceptor(new ReceivedCookies(context))
                .build();
    } /**
     * a http client contains cookie store and use
     */
    public static OkHttpClient useCookieClient(@NonNull Context context) {
        /**
         * Somewhere you create a new OkHttpClient and use it on all your requests.
         */
        return new OkHttpClient.Builder()
                .addInterceptor(new AddCookies(context))
                .addInterceptor(new ReceivedCookies(context))
                .build();
    }
}
