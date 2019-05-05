package com.python.cat.accounts.logout;

import android.content.Context;

import com.python.cat.commonlib.net.domain.LoginResult;
import com.python.cat.commonlib.net.domain.LogoutResult;
import com.python.cat.commonlib.net.http.WanRequest;

import io.reactivex.Flowable;

public class LogoutEngine {


    public static Flowable<LogoutResult> logout(Context context) {
        return WanRequest.getInstance().logout(context);
    }
}
