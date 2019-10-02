package com.wzh.http_lib;


/**
 * 服务异常类
 */
public class ServerException extends RuntimeException {
    int code;
    String message;


    public ServerException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
