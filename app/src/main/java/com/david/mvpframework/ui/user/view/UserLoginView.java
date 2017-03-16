package com.david.mvpframework.ui.user.view;

import com.david.mvpframework.ui.base.BaseView;
import com.david.mvpframework.ui.user.been.response.UserLoginResponse;

/**
 * 创建人：行者.
 * 创建时间： 2017/3/3.
 * 邮箱：qjxu@elitect.com
 */
public interface UserLoginView extends BaseView {

    /**
     * 网络请求返回成功
     */
    void onSuccess(UserLoginResponse response);

    /**
     * 请求返回失败
     *
     * @param message
     */
    void onFailure(String message);
}
