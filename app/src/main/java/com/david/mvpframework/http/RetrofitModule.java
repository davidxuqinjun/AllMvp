package com.david.mvpframework.http;

import android.util.Log;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 *
 */
@Module
public class RetrofitModule {

    @Singleton
    @Provides
    public MyRetrofit providesMyRetrofit(@Named("cache"/*"default"*/) OkHttpClient okHttpClient) {
        Log.i("TAG", "=========执行providesMyRetrofit========");
        return new MyRetrofit(okHttpClient);
    }

    /*@Singleton
    @Provides
    public TaobaoRetrofit providerTaobaoRetrofit(@Named("cache") OkHttpClient okHttpClient) {
        return new TaobaoRetrofit(okHttpClient);
    }*/
}
