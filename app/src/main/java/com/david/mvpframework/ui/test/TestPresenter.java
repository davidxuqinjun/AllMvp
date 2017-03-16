package com.david.mvpframework.ui.test;

import android.content.Context;

import com.david.mvpframework.http.HttpCallback;
import com.david.mvpframework.ui.base.BasePresenter;
import com.david.mvpframework.ui.launch.been.request.AppUpdateRequest;
import com.david.mvpframework.ui.launch.been.response.AppUpdateResponse;
import com.david.mvpframework.ui.launch.httpService.LaunchService;

import javax.inject.Inject;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/28.
 * 邮箱：342211385@qq.com
 */

public class TestPresenter extends BasePresenter {

    private TestView testView;
    private LaunchService launchService;
    private Context context;

    @Inject
    public TestPresenter(TestView testView, LaunchService launchService, Context context) {
        this.testView = testView;
        this.launchService = launchService;
        this.context = context;
    }

    public void queryAppUpdate(AppUpdateRequest request) {
        addSubscription(launchService.getAppUpdateInfo(request), new HttpCallback<AppUpdateResponse>(context) {
            @Override
            public void onSuccess(AppUpdateResponse response) {
                testView.onSuccess(response);
            }

            @Override
            public void onFailure(String msg) {
                testView.hideLoading();
            }

            @Override
            public void onFinish() {
                testView.hideLoading();
            }

            @Override
            public void onLaunch() {
                //开始
                testView.showLoading();
            }
        });
    }

}
