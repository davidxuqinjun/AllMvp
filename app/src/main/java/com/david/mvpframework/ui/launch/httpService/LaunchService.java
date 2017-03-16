package com.david.mvpframework.ui.launch.httpService;

import com.david.mvpframework.ui.launch.been.request.AppUpdateRequest;
import com.david.mvpframework.ui.launch.been.response.AppUpdateResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 接口service
 * 创建人：行者.
 * 创建时间： 2017/2/6.
 * 邮箱：342211385@qq.com
 */

public interface LaunchService {

    @POST("version/queryAppIsUpdate.do")
    Observable<AppUpdateResponse> getAppUpdateInfo(@Body AppUpdateRequest request);
}
