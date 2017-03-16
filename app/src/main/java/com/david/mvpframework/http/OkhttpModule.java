package com.david.mvpframework.http;

import android.content.Context;
import android.text.TextUtils;

import com.david.mvpframework.common.Constant;
import com.david.mvpframework.utils.MySharedPreferences;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Okio;

/**
 * <b>类名称：</b> OkhttpModule <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月12日 下午3:48<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class OkhttpModule {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    MySharedPreferences preferences;

    Context context;

    public OkhttpModule(Context context) {
        this.context = context;
        preferences = new MySharedPreferences(context);
    }

    @Singleton
    @Provides
    @Named("cache")
    public OkHttpClient providesAutoCacheOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request.header("Content-Type: application/json, Accept: application/json");
                Response response = chain.proceed(request);
                String cacheControl = request.cacheControl().toString();
                if (TextUtils.isEmpty(cacheControl)) {
                    cacheControl = "public, max-age=" + 3600 * 6 + " ,max-stale=2419200";
                }
                return response.newBuilder().header("Cache-Control", cacheControl).removeHeader("Pragma").build();
            }
        };
        Interceptor cookieInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //获取cookie
                String cookie = preferences.getString("ssUserSessionKey", "");
                Request request = chain.request()
                        .newBuilder().addHeader("ssUserSessionKey", cookie).build();
                return chain.proceed(request);
            }
        };

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .addInterceptor(cookieInterceptor)//放入cookie
                .retryOnConnectionFailure(true)
                .connectTimeout(Constant.HttpTime.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(Constant.HttpTime.READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.HttpTime.WRITE_TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    @Named("default")
    public OkHttpClient providesOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                // Customize the request
                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .removeHeader("Pragma")
                        .header("Cache-Control", String.format("max-age=%d", 432000))
                        .build();
                Response response = chain.proceed(request);

                String realResponseString = response.body().string();
                InputStream stream = new ByteArrayInputStream(realResponseString.getBytes(UTF8));
                return response.newBuilder()
                        .body(new RealResponseBody(request.headers(), Okio.buffer(Okio.source(stream))))
                        .build();
            }
        };
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addInterceptor(cacheInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(10000, TimeUnit.SECONDS)
                .build();
    }
}
