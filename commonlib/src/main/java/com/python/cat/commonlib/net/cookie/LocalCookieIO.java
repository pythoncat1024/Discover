package com.python.cat.commonlib.net.cookie;

import android.content.Context;
import android.preference.PreferenceManager;

import java.util.HashSet;

/**
 * save cookie 2 sp, clear cookie from sp
 */
public class LocalCookieIO {
    public static final String COOKIE_KEY = "store_cookie_in_sp";
    public static void saveCookie(Context context, HashSet<String> cookies) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putStringSet(COOKIE_KEY, cookies)
                .apply();
    }

    public static void clearCookie(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .remove(COOKIE_KEY)
                .apply();

    }
}
