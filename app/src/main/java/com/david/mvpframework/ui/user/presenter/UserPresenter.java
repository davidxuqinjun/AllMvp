package com.david.mvpframework.ui.user.presenter;

import com.david.mvpframework.ui.base.BasePresenter;

/**
 * 创建人：行者.
 * 创建时间： 2017/3/3.
 * 邮箱：qjxu@elitect.com
 */
public class UserPresenter<V> extends BasePresenter<V> {

    private V mvpView;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }


    public void detachView() {
        this.mvpView = null;
        onUnsubscribe();
    }
}
