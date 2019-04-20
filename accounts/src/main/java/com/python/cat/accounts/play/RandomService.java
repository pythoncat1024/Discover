package com.python.cat.accounts.play;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 随机获取一个在线图片地址
 */
public interface RandomService {
    String GAN_URI = "http://gank.io/api/";

    @GET("random/data/福利/{size}")
    public Flowable<Play> getSplashData(@Path("size") int size);

    class ServiceImpl implements RandomService {

        private final Retrofit retrofit;

        private ServiceImpl() {
            retrofit = new Retrofit.Builder()
                    .baseUrl(GAN_URI)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        public static RandomService getInstance() {
            return new ServiceImpl();
        }

        @Override
        public Flowable<Play> getSplashData(int size) {
            return retrofit.create(RandomService.class)
                    .getSplashData(size)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    ;

        }
    }
}
