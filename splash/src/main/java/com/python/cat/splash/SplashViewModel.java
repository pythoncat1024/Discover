package com.python.cat.splash;

import androidx.lifecycle.ViewModel;

import com.apkfuns.logutils.LogUtils;

import io.reactivex.Flowable;

public class SplashViewModel extends ViewModel {

    @Override
    protected void onCleared() {
        super.onCleared();
        LogUtils.i("cleared...view model");
    }


    Flowable<Splash> getSplash(int size) {

        return SplashService.SplashImpl.getInstance().getSplashData(size);
    }
}
