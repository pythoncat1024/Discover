package com.pycat.phone.vm;

import android.preference.PreferenceManager;

import androidx.lifecycle.ViewModel;

import com.apkfuns.logutils.LogUtils;
import com.pycat.phone.base.PhoneApp;
import com.pycat.phone.common.Constant;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class PhoneViewModel extends ViewModel {

    @Override
    protected void onCleared() {
        super.onCleared();
        LogUtils.w("cleared....");
    }

    public boolean splashDone() {
        return PreferenceManager.getDefaultSharedPreferences(PhoneApp.get())
                .getBoolean(Constant.SPLASH_DONE, false);
    }

    /**
     * 状态写入sp，下次进入就不走欢迎页了
     */
    public void setSplashDone() {
        PreferenceManager
                .getDefaultSharedPreferences(PhoneApp.get())
                .edit()
                .putBoolean(Constant.SPLASH_DONE, true)
                .apply();
    }


    public Disposable delay(int seconds, Consumer<Long> consumer) {

        return Flowable.timer(seconds, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }
}
