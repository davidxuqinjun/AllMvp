package com.david.mvpframework.ui.launch.view;

import com.david.mvpframework.ui.base.BaseView;
import com.david.mvpframework.ui.launch.been.response.AppUpdateResponse;

/**
 * 创建人：行者.
 * 创建时间： 2017/2/27.
 * 邮箱：342211385@qq.com
 */

public interface AppUpdateView extends BaseView {
    /**
     * 网络请求返回成功
     */
    void onSuccess(AppUpdateResponse response);

    /**
     * 请求返回失败
     *
     * @param message
     */
    void onFailure(String message);
}
