package com.python.cat.accounts.logout;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.apkfuns.logutils.LogUtils;
import com.python.cat.commonlib.net.domain.LoginResult;
import com.python.cat.commonlib.net.domain.LogoutResult;

import io.reactivex.Flowable;

public class LogoutViewModel extends ViewModel {

    @Override
    protected void onCleared() {
        super.onCleared();
    }


    public Flowable<LogoutResult> logout(Context context) {
        LogUtils.e("logout");
        return LogoutEngine.logout(context);
    }

}
