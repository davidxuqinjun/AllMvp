package com.david.mvpframework.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/28.
 * 邮箱：342211385@qq.com
 */

public class MvpActivity<T extends BasePresenter> extends BaseActivity {

    @Inject
    public T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void initViewData() {
        
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onUnsubscribe();
    }
}
