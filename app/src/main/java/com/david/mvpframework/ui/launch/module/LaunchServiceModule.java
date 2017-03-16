package com.david.mvpframework.ui.launch.module;

import com.david.mvpframework.http.MyRetrofit;
import com.david.mvpframework.ui.launch.httpService.LaunchService;
import com.david.mvpframework.ui.launch.scope.LaunchScope;

import dagger.Module;
import dagger.Provides;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/27.
 * 邮箱：342211385@qq.com
 */
@Module
public class LaunchServiceModule {

    public LaunchServiceModule() {

    }

    @LaunchScope
    @Provides
    public LaunchService provideLauncherService(MyRetrofit retrofit) {
        return retrofit.getRetrofit().create(LaunchService.class);
    }
}
