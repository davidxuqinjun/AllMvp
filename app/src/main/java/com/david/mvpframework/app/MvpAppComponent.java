package com.david.mvpframework.app;

import android.content.Context;

import com.david.mvpframework.http.MyRetrofit;
import com.david.mvpframework.http.OkhttpModule;
import com.david.mvpframework.http.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/8.
 * 邮箱：342211385@qq.com
 */
@Singleton
@Component(modules = {MvpAppModule.class, OkhttpModule.class, RetrofitModule.class})
public interface MvpAppComponent {

    MvpAppApplication getApplication();

    Context getContext();

    //全局初始化MyRetrofit实例
    MyRetrofit getMyRetrofit();
}
