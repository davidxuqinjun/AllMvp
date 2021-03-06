package com.david.mvpframework.ui.user.activitys;

import android.os.Bundle;

import com.david.mvpframework.R;
import com.david.mvpframework.app.MvpAppApplication;
import com.david.mvpframework.app.MvpAppComponent;
import com.david.mvpframework.ui.base.MvpActivity;
import com.david.mvpframework.ui.user.been.response.UserLoginResponse;
import com.david.mvpframework.ui.user.component.DaggerUserComponent;
import com.david.mvpframework.ui.user.module.UserLoginModule;
import com.david.mvpframework.ui.user.module.UserServiceModule;
import com.david.mvpframework.ui.user.presenter.UserLoginPresenter;
import com.david.mvpframework.ui.user.view.UserLoginView;

import butterknife.ButterKnife;

public class UserLoginActivity extends MvpActivity<UserLoginPresenter> implements UserLoginView {

    private MvpAppApplication application;

    //@Inject
    //UserLoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        initViewData();
    }

    @Override
    public void initViewData() {
        ButterKnife.bind(this);
        application = (MvpAppApplication) this.getApplication();

        MvpAppComponent component = application.getMvpAppComponent();
        DaggerUserComponent.builder().mvpAppComponent(component)
                .userLoginModule(new UserLoginModule(this))
                .userServiceModule(new UserServiceModule())
                .build()
                .inject(this);

        //用户登录
        //presenter.userLogin();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(UserLoginResponse response) {

    }

    @Override
    public void onFailure(String message) {

    }
}
