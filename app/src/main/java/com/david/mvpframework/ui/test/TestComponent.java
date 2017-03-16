package com.david.mvpframework.ui.test;

import com.david.mvpframework.app.SmartSDNComponent;
import com.david.mvpframework.ui.launch.module.LaunchServiceModule;
import com.david.mvpframework.ui.launch.scope.LaunchScope;

import dagger.Component;

/**
 * 创建人：行者.
 * 创建时间： 2017/3/15.
 * 邮箱：qjxu@elitect.com
 */
@LaunchScope
@Component(dependencies = SmartSDNComponent.class,
        modules = {TestModule.class, LaunchServiceModule.class})
public interface TestComponent {

    void inject(TestActivity testActivity);

}
