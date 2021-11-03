package com.diu.crowd.exception;

/**
 * 登录异常处理类
 *
 * @author DIU
 * @date 2021/11/1 21:41
 */
public class LoginFailedException extends RuntimeException {

    private static final long serialVersionUID = -4606647660142235420L;

    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    public LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
