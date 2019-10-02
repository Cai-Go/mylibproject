package com.wzh.http_lib.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加公共参数
 */
public class AddQueryParameterInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request request;
        HttpUrl httpUrl = originalRequest.url().newBuilder()
                //此处可以继续添加其他点参数
                .addQueryParameter("appVersion", "1.0.0")
                .build();
        request = originalRequest.newBuilder().url(httpUrl).build();
        return chain.proceed(request);
    }
}
