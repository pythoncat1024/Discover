package com.python.cat.accounts.login;

import androidx.lifecycle.ViewModel;

import io.reactivex.Flowable;

public class LoginViewModel extends ViewModel {

    @Override
    protected void onCleared() {
        super.onCleared();
    }


    public Flowable<LoginResult> login(String username, String password) {
        // TODO: 2019-04-20 need logic
        return null;
    }

}
