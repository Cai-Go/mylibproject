package com.wzh.http_lib;

import android.util.Log;

import io.reactivex.functions.Function;

/**
 * 提取data部分的数据
 */
public class HttpResultFunc<T> implements Function<BaseResult<T>, T> {
    @Override
    public T apply(BaseResult<T> tBaseResult) {
        Log.d("Result", "Result:" + tBaseResult.getData());
        if (tBaseResult.getStatus() != 200) {
            throw new ServerException(tBaseResult.getStatus(), tBaseResult.getMessage());
        }
        return tBaseResult.getData();
    }
}
