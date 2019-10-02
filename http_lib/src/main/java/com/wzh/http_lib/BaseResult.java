package com.wzh.http_lib;

/**
 * 实体类基类
 */
public class BaseResult<T> {
    private static int SUCCESS_CODE = 200;//成功的code
    private String message;
    private T data;

    private int status;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return getStatus() == SUCCESS_CODE;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
