package com.qlu.edu.domanagement.service.ex;

public class NotIsSuException extends ServiceException{
    public NotIsSuException() {
    }

    public NotIsSuException(String message) {
        super(message);
    }

    public NotIsSuException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotIsSuException(Throwable cause) {
        super(cause);
    }

    public NotIsSuException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
