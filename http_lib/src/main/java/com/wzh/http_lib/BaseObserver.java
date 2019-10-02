package com.wzh.http_lib;

import android.content.Context;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Observer的封装
 *
 * @param <T>
 */
public abstract class BaseObserver<T> implements Observer<T> {
    protected Context context;

    public BaseObserver() {
    }

    public BaseObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    @Override
    public void onNext(T t) {
        try {
            onSuccess(t);
        } catch (Exception e) {
            e.printStackTrace();
            onError(e);
        }
    }

    @Override
    public void onError(Throwable e) {
        onRequestEnd();
        try {
            if (e instanceof HttpException) {
                HttpException httpException = (HttpException) e;
                switch (httpException.code()) {
                    case HttpStatusCode.UNAUTHORIZED:
                    case HttpStatusCode.FORBIDDEN:
                    case HttpStatusCode.NOT_FOUND:
                    case HttpStatusCode.REQUEST_TIMEOUT:
                    case HttpStatusCode.GATEWAY_TIMEOUT:
                    case HttpStatusCode.INTERNAL_SERVER_ERROR:
                    case HttpStatusCode.BAD_GATEWAY:
                    case HttpStatusCode.SERVICE_UNAVAILABLE:
                    default:
                        onFailure(e, true, httpException.code(), "网络异常");
                        break;
                }
            } else if (e instanceof ServerException) {
                ServerException serverException = (ServerException) e;
                onFailure(e, true, serverException.code, serverException.message);
            } else if (e instanceof JsonParseException || e instanceof JSONException) {
                onFailure(e, false, HttpStatusCode.JSONEXCEPTION_CODE, "解析错误");
            } else if (e instanceof ConnectException) {
                onFailure(e, true, HttpStatusCode.CONNECTEXCEPTION_CODE, "连接失败");
            } else if (e instanceof SSLHandshakeException) {
                onFailure(e, true, HttpStatusCode.SSLHANDSHAKEEXCEPTION_CODE, "证书验证失败");
            } else if (e instanceof NullPointerException) {
                onFailure(e, false, HttpStatusCode.NULLPOINTEREXCEPTION_CODE, "空指针异常");
            } else {
                onFailure(e, false, HttpStatusCode.UNKONWEXCEPTION_CODE, "未知错误");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    @Override
    public void onComplete() {
    }


    /**
     * 成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccess(T t) throws Exception;

    /**
     * 返回失败
     *
     * @param e
     * @param isHttpError
     * @param code
     * @param Message
     * @throws Exception
     */
    protected abstract void onFailure(Throwable e, boolean isHttpError, int code, String Message) throws Exception;

    /**
     * 开始请求
     */
    protected void onRequestStart() {

    }

    /**
     * 结束请求
     */
    protected void onRequestEnd() {
    }
}
