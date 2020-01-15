package com.qlu.edu.domanagement.service.ex;

public class oldPasswordNotMatchException extends ServiceException {
    public oldPasswordNotMatchException() {
    }

    public oldPasswordNotMatchException(String message) {
        super(message);
    }

    public oldPasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public oldPasswordNotMatchException(Throwable cause) {
        super(cause);
    }

    public oldPasswordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
