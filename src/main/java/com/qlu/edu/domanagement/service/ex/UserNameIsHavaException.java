package com.qlu.edu.domanagement.service.ex;

public class UserNameIsHavaException extends ServiceException{
    public UserNameIsHavaException() {
    }

    public UserNameIsHavaException(String message) {
        super(message);
    }

    public UserNameIsHavaException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameIsHavaException(Throwable cause) {
        super(cause);
    }

    public UserNameIsHavaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
