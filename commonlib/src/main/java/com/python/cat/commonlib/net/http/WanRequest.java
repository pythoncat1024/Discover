package com.python.cat.commonlib.net.http;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import com.python.cat.commonlib.net.cookie.HttpClient;
import com.python.cat.commonlib.net.domain.LoginResult;
import com.python.cat.commonlib.net.domain.LogoutResult;
import com.python.cat.commonlib.net.domain.RegisterResult;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
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
    private Retrofit retrofitStoreCookie(@NonNull Context context) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(WanService.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(HttpClient.storeCookieClient(context))
                    .build();
        }
        return retrofit;
    }

    @WorkerThread
    private Retrofit retrofitUseCookie(@NonNull Context context) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(WanService.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient.Builder().build())
                    .build();
        }
        return retrofit;
    }

    public Flowable<LoginResult> login(@NonNull Context context,
                                       String username, String password) {
        /*
         .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
         */
        return retrofitStoreCookie(context)
                .create(WanService.class)
                .login(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Flowable<RegisterResult> register(@NonNull Context context,
                                             String username, String password, String rePassword) {
        return retrofitStoreCookie(context)
                .create(WanService.class)
                .register(username, password, rePassword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Flowable<LogoutResult> logout(Context context) {
        /*
         .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
         */
        return retrofitStoreCookie(context)
                .create(WanService.class)
                .logout()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Flowable addTodo(Context context, String title,
                            String content,
                            String date,
                            int type) {
        return retrofitUseCookie(context).create(WanService.class).addTodo(title, content, date, type)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());
    }

}
