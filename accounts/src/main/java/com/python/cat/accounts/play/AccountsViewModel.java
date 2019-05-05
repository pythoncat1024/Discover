package com.python.cat.accounts.play;

import android.content.Context;
import android.preference.PreferenceManager;

import androidx.lifecycle.ViewModel;

import com.python.cat.commonlib.net.cookie.LocalCookieIO;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AccountsViewModel extends ViewModel {

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public Flowable<Play> getImageUri() {
        return RandomService.ServiceImpl.getInstance()
                .getSplashData(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public boolean online(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .contains(LocalCookieIO.COOKIE_KEY);
    }
}
