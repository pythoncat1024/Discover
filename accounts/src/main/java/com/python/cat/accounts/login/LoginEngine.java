package com.python.cat.accounts.login;

import android.content.Context;

import com.python.cat.commonlib.net.domain.LoginResult;
import com.python.cat.commonlib.net.http.WanRequest;

import io.reactivex.Flowable;

public class LoginEngine {


    public static Flowable<LoginResult> login(Context context, String username, String password) {
        return WanRequest.getInstance().login(context, username, password);
    }
}
