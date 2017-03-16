package com.david.mvpframework.ui.launch.presenter;

import android.content.Context;

import com.david.mvpframework.http.HttpCallback;
import com.david.mvpframework.ui.launch.been.request.AppUpdateRequest;
import com.david.mvpframework.ui.launch.been.response.AppUpdateResponse;
import com.david.mvpframework.ui.launch.httpService.LaunchService;
import com.david.mvpframework.ui.launch.view.AppUpdateView;

import javax.inject.Inject;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/27.
 * 邮箱：342211385@qq.com
 */
public class AppVersionUpdatePresenter extends LaunchPresenter<AppUpdateView> {
    private AppUpdateView view;
    private LaunchService launchService;

    private Context context;

    @Inject
    public AppVersionUpdatePresenter(AppUpdateView view, LaunchService launchService, Context context) {
        this.view = view;
        this.launchService = launchService;
        this.context = context;
    }

    public void queryAppUpdae(AppUpdateRequest request) {
        addSubscription(launchService.getAppUpdateInfo(request), new HttpCallback<AppUpdateResponse>(context) {
            @Override
            public void onSuccess(AppUpdateResponse response) {
                view.onSuccess(response);
            }

            @Override
            public void onFailure(String msg) {
                view.hideLoading();
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }

            @Override
            public void onLaunch() {
                //开始
                view.showLoading();
            }
        });
    }
}
