package com.david.mvpframework.app;

import android.app.Application;

import com.david.mvpframework.http.OkhttpModule;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/6.
 * 邮箱：342211385@qq.com
 */

public class SmartSDNApplication extends Application {

    private SmartSDNComponent smartSDNComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initSmartSDNComponent();
    }

    public void initSmartSDNComponent() {
        smartSDNComponent = DaggerSmartSDNComponent.builder().smartSDNModule(new SmartSDNModule(this))
                .okhttpModule(new OkhttpModule(this))
                .build();
    }


    public SmartSDNComponent getSmartSDNComponent() {
        return smartSDNComponent;
    }

}
