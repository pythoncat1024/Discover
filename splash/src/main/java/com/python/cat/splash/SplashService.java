package com.python.cat.splash;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SplashService {

    // http://gank.io/api/random/data/福利/3
    String GAN_URI = "http://gank.io/api/";

    @GET("random/data/福利/{size}")
    public Flowable<Splash> getSplashData(@Path("size") int size);

    class SplashImpl implements SplashService {

        private final Retrofit retrofit;

        private SplashImpl() {
            retrofit = new Retrofit.Builder()
                    .baseUrl(GAN_URI)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        public static SplashService getInstance() {
            return new SplashImpl();
        }

        @Override
        public Flowable<Splash> getSplashData(int size) {
            return retrofit.create(SplashService.class)
                    .getSplashData(size)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    ;

        }
    }
}
