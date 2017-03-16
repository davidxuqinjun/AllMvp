package com.david.mvpframework.ui.test;

import android.os.Bundle;

import com.david.mvpframework.R;
import com.david.mvpframework.app.SmartSDNApplication;
import com.david.mvpframework.app.SmartSDNComponent;
import com.david.mvpframework.ui.base.MvpActivity;
import com.david.mvpframework.ui.launch.been.response.AppUpdateResponse;
import com.david.mvpframework.ui.launch.module.LaunchServiceModule;
import com.david.mvpframework.utils.SmartSDNLoger;

import javax.inject.Inject;

public class TestActivity extends MvpActivity<TestPresenter> implements TestView {

    private SmartSDNApplication application;

    @Inject
    TestPresenter testPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        application = (SmartSDNApplication) getApplication();

        SmartSDNComponent component = application.getSmartSDNComponent();
        DaggerTestComponent.builder().smartSDNComponent(application.getSmartSDNComponent())
                .launchServiceModule(new LaunchServiceModule())
                .testModule(new TestModule(this))
                .build()
                .inject(this);

        SmartSDNLoger.debug("=====presenter=======" + presenter.getClass().getSimpleName());
        SmartSDNLoger.debug("=====testPresenter=======" + testPresenter.getClass().getSimpleName());


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(AppUpdateResponse response) {

    }

    @Override
    public void onFailure(String message) {

    }
}
