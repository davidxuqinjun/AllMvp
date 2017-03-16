package com.david.mvpframework.ui.user.httpservice;

import com.david.mvpframework.ui.user.been.request.UserLoginRequest;
import com.david.mvpframework.ui.user.been.response.UserLoginResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 创建人：行者.
 * 创建时间： 2017/3/3.
 * 邮箱：qjxu@elitect.com
 */

public interface UserService {

    @POST("auth/login.do")
    Observable<UserLoginResponse> userLogin(@Body UserLoginRequest request);
}
