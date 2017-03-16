package com.david.mvpframework.ui.user.module;

import com.david.mvpframework.http.MyRetrofit;
import com.david.mvpframework.ui.user.Scope.UserScope;
import com.david.mvpframework.ui.user.httpservice.UserService;

import dagger.Module;
import dagger.Provides;

/**
 * 创建人：行者.
 * 创建时间： 2017/3/3.
 * 邮箱：qjxu@elitect.com
 */
@Module
public class UserServiceModule {

    public UserServiceModule() {

    }

    @UserScope
    @Provides
    public UserService provideLauncherService(MyRetrofit retrofit) {
        return retrofit.getRetrofit().create(UserService.class);
    }
}
