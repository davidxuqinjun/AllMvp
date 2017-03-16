package com.david.mvpframework.http;

import com.david.mvpframework.common.Constant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Retrofit请求设置
 */
public class MyRetrofit {
    //private static final String BASE_URL = "http://xxxxxx.xxx.xxxx/";
    //private static final String BASE_URL = "http://192.168.0.163:8080/";
    private static Retrofit retrofit;

    public MyRetrofit(OkHttpClient okHttpClient) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.HttpURL.BASE_URL)//总地址
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }
}
