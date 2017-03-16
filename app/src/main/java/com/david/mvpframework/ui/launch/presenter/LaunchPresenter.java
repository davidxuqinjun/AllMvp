package com.david.mvpframework.ui.launch.presenter;

import com.david.mvpframework.ui.base.BasePresenter;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/6.
 * 邮箱：342211385@qq.com
 */

public class LaunchPresenter<V> extends BasePresenter<V> {
    private V mvpView;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }


    public void detachView() {
        this.mvpView = null;
        onUnsubscribe();
    }
}
