package com.david.mvpframework.ui.user.module;

import com.david.mvpframework.ui.user.Scope.UserScope;
import com.david.mvpframework.ui.user.view.UserLoginView;

import dagger.Module;
import dagger.Provides;

/**
 * 创建人：行者.
 * 创建时间： 2017/3/3.
 * 邮箱：342211385@qq.com
 */
@Module
public class UserLoginModule {
    private UserLoginView userLoginView;

    public UserLoginModule(UserLoginView loginView) {
        this.userLoginView = loginView;
    }

    @UserScope
    @Provides
    public UserLoginView provideUserLoginModule() {
        return userLoginView;
    }
}
