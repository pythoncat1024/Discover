package com.python.cat.commonlib.net.http;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import com.python.cat.commonlib.net.cookie.HttpClient;
import com.python.cat.commonlib.net.domain.LoginResult;
import com.python.cat.commonlib.net.domain.RegisterResult;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WanRequest {

    private WanRequest() {
    }

    public static WanRequest getInstance() {
        return new WanRequest();
    }

    private Retrofit retrofit = null;

    @WorkerThread
    private Retrofit getRetrofit(@NonNull Context context) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(WanService.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(HttpClient.cookieClient(context))
                    .build();
        }
        return retrofit;
    }

    public Flowable<LoginResult> login(Context context, String username,
                                       String password) {
        /*
        .subscribeOn(Schedulers.io()) .observeOn(AppSchedulers.mainThread())
         */
        return getRetrofit(context)
                .create(WanService.class)
                .login(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Flowable<RegisterResult> register(Context context, String username,
                                             String password, String rePassword) {
        /*
        .subscribeOn(Schedulers.io()) .observeOn(AppSchedulers.mainThread())
         */
        return getRetrofit(context)
                .create(WanService.class)
                .register(username, password,rePassword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    public Flowable addTodo(Context context, String title,
                            String content,
                            String date,
                            int type) {
        return getRetrofit(context).create(WanService.class).addTodo(title, content, date, type)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());
    }

}
