package com.david.mvpframework.app;

import android.content.Context;

import com.david.mvpframework.utils.MySharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/8.
 * 邮箱：342211385@qq.com
 */
@Module
public class MvpAppModule {
    private Context context;

    public MvpAppModule(MvpAppApplication context) {
        this.context = context;
    }

    @Provides
    @Singleton
    MvpAppApplication provideApplication() {
        return (MvpAppApplication) context.getApplicationContext();
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    MySharedPreferences provideMySharedPreferences() {
        return new MySharedPreferences(context);
    }

}
