package com.python.cat.accounts.register;

import androidx.lifecycle.ViewModel;

import io.reactivex.Flowable;

public class RegisterViewModel extends ViewModel {


    @Override
    protected void onCleared() {
        super.onCleared();
    }


    public Flowable<RegisterResult> register(String username, String password, String rePassword) {
        // TODO: 2019-04-20  need logic
        return null;
    }
}
