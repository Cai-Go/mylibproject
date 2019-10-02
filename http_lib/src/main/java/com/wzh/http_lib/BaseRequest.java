package com.wzh.http_lib;

import com.wzh.http_lib.interceptor.AddQueryParameterInterceptor;
import com.wzh.http_lib.interceptor.InterceptorUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 请求方法
 */
public class BaseRequest {
    private static BaseRequest instance;


    /**
     * retrofit底层用的okHttp,所以设置超时还需要okHttp
     * 然后设置5秒超时
     * 其中DEFAULT_TIMEOUT是我这边定义的一个常量
     * TimeUnit为java.util.concurrent包下的时间单位
     * TimeUnit.SECONDS这里为秒的单位
     */

    private OkHttpClient client = new OkHttpClient.Builder()
            //设置连接超时时间
            .connectTimeout(20, TimeUnit.SECONDS)
            //设置读取超时时间
            .readTimeout(20, TimeUnit.SECONDS)
            //设置写入超时时间
            .writeTimeout(20, TimeUnit.SECONDS)
            //添加其他拦截器
            .addInterceptor(InterceptorUtil.HeaderInterceptor())
            //添加日志拦截器
            .addInterceptor(InterceptorUtil.LogInterceptor())
            //添加公共参数拦截器
            .addInterceptor(new AddQueryParameterInterceptor())
            .build();


    private Retrofit retrofit = new Retrofit.Builder()
            //添加 Gson 转换器
            .addConverterFactory(GsonConverterFactory.create())
            //添加 Rx 转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            //baseUrl
            .baseUrl(ApiServise.API_SERVER_URL)
            .client(client)
            .build();

    private ApiServise apiServise = retrofit.create(ApiServise.class);

    public static synchronized BaseRequest getInstance() {
        if (instance == null) {
            instance = new BaseRequest();
        }
        return instance;
    }


    public ApiServise getApiServise() {
        return apiServise;
    }
}
