package com.diu.crowd.exception;

/**
 * @author DIU
 * @date 2021/11/4 20:16
 */
public class LoginAcctAlreadyInUseException extends RuntimeException {
    private static final long serialVersionUID = -6939968797016663686L;

    public LoginAcctAlreadyInUseException() {
    }

    public LoginAcctAlreadyInUseException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUseException(Throwable cause) {
        super(cause);
    }

    public LoginAcctAlreadyInUseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
