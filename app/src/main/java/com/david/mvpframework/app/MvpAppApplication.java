package com.david.mvpframework.app;

import android.app.Application;

import com.david.mvpframework.http.OkhttpModule;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/6.
 * 邮箱：342211385@qq.com
 */

public class MvpAppApplication extends Application {

    private MvpAppComponent smartSDNComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initSmartSDNComponent();
    }

    public void initSmartSDNComponent() {

        smartSDNComponent = DaggerMvpAppComponent.builder()
                .mvpAppModule(new MvpAppModule(this))
                .okhttpModule(new OkhttpModule(this))
                .build();
    }


    public MvpAppComponent getMvpAppComponent() {
        return smartSDNComponent;
    }

}
