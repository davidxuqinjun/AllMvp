package com.david.mvpframework.ui.user.presenter;

import android.content.Context;

import com.david.mvpframework.http.HttpCallback;
import com.david.mvpframework.ui.user.been.request.UserLoginRequest;
import com.david.mvpframework.ui.user.been.response.UserLoginResponse;
import com.david.mvpframework.ui.user.httpservice.UserService;
import com.david.mvpframework.ui.user.view.UserLoginView;

import javax.inject.Inject;

/**
 * 创建人：行者.
 * 创建时间： 2017/3/3.
 * 邮箱：qjxu@elitect.com
 */
public class UserLoginPresenter extends UserPresenter {

    private UserService userService;
    private UserLoginView loginView;
    private Context context;

    @Inject
    public UserLoginPresenter(UserLoginView view, UserService userService, Context context) {
        this.loginView = view;
        this.userService = userService;
        this.context = context;
    }

    /**
     * 用户登录
     *
     * @param request
     */
    public void userLogin(UserLoginRequest request) {
        addSubscription(userService.userLogin(request), new HttpCallback<UserLoginResponse>(context) {
            @Override
            public void onSuccess(UserLoginResponse response) {
                loginView.onSuccess(response);
            }

            @Override
            public void onFailure(String msg) {
                loginView.onFailure(msg);
            }

            @Override
            public void onFinish() {
                loginView.showLoading();
            }

            @Override
            public void onLaunch() {
                loginView.showLoading();
            }
        });
    }
}
