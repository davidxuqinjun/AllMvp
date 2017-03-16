package com.david.mvpframework.ui.launch.module;

import com.david.mvpframework.ui.launch.scope.LaunchScope;
import com.david.mvpframework.ui.launch.view.AppUpdateView;

import dagger.Module;
import dagger.Provides;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/6.
 * 邮箱：342211385@qq.com
 */
@Module
public class LaunchModule {

    private AppUpdateView view;

    public LaunchModule(AppUpdateView view) {
        this.view = view;
    }

    @LaunchScope
    @Provides
    public AppUpdateView provideAppUpdateView() {
        return view;
    }
}
