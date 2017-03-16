package com.david.mvpframework.http;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.david.mvpframework.baseBeen.BaseResponse;
import com.david.mvpframework.ui.user.activitys.UserLoginActivity;
import com.david.mvpframework.utils.MySharedPreferences;
import com.david.mvpframework.utils.ToastUtils;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 网络请求回调拦截
 * 创建人：行者.
 * 创建时间： 2017/2/9.
 * 邮箱：qjxu@elitect.com
 */

public abstract class HttpCallback<M extends BaseResponse> extends Subscriber<M> {

    private static final int HTTP_CODE_404 = 404;
    private static final int HTTP_CODE_502 = 502;
    private static final int HTTP_CODE_504 = 504;
    private static final String ERROR_CODE_998 = "998";

    MySharedPreferences preferences;

    Context context;

    public HttpCallback(Context context) {
        this.context = context;
        preferences = new MySharedPreferences(context);
    }

    public abstract void onLaunch();

    public abstract void onSuccess(M response);

    public abstract void onFailure(String msg);

    public abstract void onFinish();

    @Override
    public void onCompleted() {
        onFinish();
    }

    @Override
    public void onNext(M response) {
        if (response.status) {
            onSuccess(response);
        } else {
            if (TextUtils.isEmpty(response.errorCode)) {
                if (!TextUtils.isEmpty(response.message)) {
                    ToastUtils.show(context, response.message);
                }
                switch (response.errorCode) {
                    case ERROR_CODE_998:
                        preferences.putString("", "");
                        final Intent intent = new Intent(context, UserLoginActivity.class);
                        //intent.putExtra(MainActivity.EXTRA_INTENT_FROM, MainActivity.LOG_OUT);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent);
                    default:
                }
            }
            onFailure(response.message);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        onLaunch();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String msg = httpException.message();
            switch (code) {
                case HTTP_CODE_404:
                    msg = "服务器异常，请稍后再试";
                    break;
                case HTTP_CODE_502:
                    msg = "网络连接超时";
                    break;
                case HTTP_CODE_504:
                    msg = "网络连接错误";
                    break;
                default:
            }
            onFailure(msg);
        } else {
            onFailure(e.getMessage());
        }
        onFinish();
    }
}
