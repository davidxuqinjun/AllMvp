package com.david.mvpframework.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 创建人：行者.
 * 创建时间： 2016/10/27.
 * 邮箱：342211385@qq.com
 */

public class ToastUtils {

    private ToastUtils() {
    }

    @SuppressWarnings("static-access")
    public synchronized static void show(Context context, String text) {
        Toast mToast = new Toast(context);
        mToast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
