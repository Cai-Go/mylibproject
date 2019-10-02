package com.wzh.http_lib;

/**
 * 网络状态码
 */
public class HttpStatusCode {
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int REQUEST_TIMEOUT = 408;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int BAD_GATEWAY = 502;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int GATEWAY_TIMEOUT = 504;

    public static final int JSONEXCEPTION_CODE = 1000;
    public static final int CONNECTEXCEPTION_CODE = 1001;
    public static final int SSLHANDSHAKEEXCEPTION_CODE = 1002;
    public static final int NULLPOINTEREXCEPTION_CODE = 1003;
    public static final int UNKONWEXCEPTION_CODE = 1004;
}
