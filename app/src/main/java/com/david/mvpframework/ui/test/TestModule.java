package com.david.mvpframework.ui.test;

import com.david.mvpframework.ui.launch.scope.LaunchScope;

import dagger.Module;
import dagger.Provides;

/**
 * 创建人：行者.
 * 创建时间： 2017/3/15.
 * 邮箱：qjxu@elitect.com
 */
@Module
public class TestModule {
    
    private TestView testView;

    public TestModule(TestView testView) {
        this.testView = testView;
    }

    @LaunchScope
    @Provides
    public TestView provideTestView() {
        return testView;
    }
}
