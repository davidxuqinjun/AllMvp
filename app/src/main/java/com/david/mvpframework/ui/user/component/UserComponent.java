package com.david.mvpframework.ui.user.component;

import com.david.mvpframework.app.MvpAppComponent;
import com.david.mvpframework.ui.user.Scope.UserScope;
import com.david.mvpframework.ui.user.activitys.UserLoginActivity;
import com.david.mvpframework.ui.user.module.UserLoginModule;
import com.david.mvpframework.ui.user.module.UserServiceModule;

import dagger.Component;

/**
 * 创建人：行者.
 * 创建时间： 2017/3/3.
 * 邮箱：342211385@qq.com
 */
@UserScope
@Component(dependencies = MvpAppComponent.class,
        modules = {UserServiceModule.class, UserLoginModule.class})
public interface UserComponent {

    void inject(UserLoginActivity userLoginActivity);
}

