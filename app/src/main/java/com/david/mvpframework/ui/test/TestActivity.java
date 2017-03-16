package com.david.mvpframework.ui.test;

import android.os.Bundle;

import com.david.mvpframework.R;
import com.david.mvpframework.app.MvpAppApplication;
import com.david.mvpframework.app.MvpAppComponent;
import com.david.mvpframework.ui.base.MvpActivity;
import com.david.mvpframework.ui.launch.been.response.AppUpdateResponse;
import com.david.mvpframework.ui.launch.module.LaunchServiceModule;
import com.david.mvpframework.utils.MvpLoger;

import javax.inject.Inject;

public class TestActivity extends MvpActivity<TestPresenter> implements TestView {

    private MvpAppApplication application;

    @Inject
    TestPresenter testPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        application = (MvpAppApplication) getApplication();

        MvpAppComponent component = application.getMvpAppComponent();
        DaggerTestComponent.builder().mvpAppComponent(component)
                .launchServiceModule(new LaunchServiceModule())
                .testModule(new TestModule(this))
                .build()
                .inject(this);

        MvpLoger.debug("=====presenter=======" + presenter.getClass().getSimpleName());
        MvpLoger.debug("=====testPresenter=======" + testPresenter.getClass().getSimpleName());


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
