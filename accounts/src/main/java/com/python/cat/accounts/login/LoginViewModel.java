package com.python.cat.accounts.login;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.apkfuns.logutils.LogUtils;
import com.python.cat.commonlib.net.domain.LoginResult;

import io.reactivex.Flowable;

public class LoginViewModel extends ViewModel {

    @Override
    protected void onCleared() {
        super.onCleared();
    }


    public Flowable<LoginResult> login(Context context, String username, String password) {
        LogUtils.e("login::: %s,%s", username, password);
        return LoginEngine.login(context, username, password);
    }

}
