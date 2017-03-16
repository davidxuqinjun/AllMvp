package com.david.mvpframework.ui.launch.component;

import com.david.mvpframework.app.SmartSDNComponent;
import com.david.mvpframework.ui.launch.LauncherActivity;
import com.david.mvpframework.ui.launch.module.LaunchModule;
import com.david.mvpframework.ui.launch.module.LaunchServiceModule;
import com.david.mvpframework.ui.launch.scope.LaunchScope;

import dagger.Component;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/27.
 * 邮箱：342211385@qq.com
 */
@LaunchScope
@Component(dependencies = SmartSDNComponent.class,
        modules = {LaunchModule.class, LaunchServiceModule.class})
public interface LaunchComponent {

    void inject(LauncherActivity launcherActivity);
}
