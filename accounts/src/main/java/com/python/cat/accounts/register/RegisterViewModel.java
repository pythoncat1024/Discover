package com.python.cat.accounts.register;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.apkfuns.logutils.LogUtils;
import com.python.cat.commonlib.net.domain.RegisterResult;
import com.python.cat.commonlib.net.http.WanRequest;

import io.reactivex.Flowable;

public class RegisterViewModel extends ViewModel {


    @Override
    protected void onCleared() {
        super.onCleared();
        LogUtils.e("super");
    }


    public Flowable<RegisterResult> register(Context context,
                                             String username, String password, String rePassword) {
        return WanRequest.getInstance().register(context, username, password, rePassword);
    }
}
