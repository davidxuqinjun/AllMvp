package com.david.mvpframework.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/6.
 * 邮箱：342211385@qq.com
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initViewData();
    }


    public abstract void initViewData();


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
