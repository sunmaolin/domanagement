package com.qlu.edu.domanagement.service.ex;

public class SidExistException extends ServiceException {
    public SidExistException() {
    }

    public SidExistException(String message) {
        super(message);
    }

    public SidExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public SidExistException(Throwable cause) {
        super(cause);
    }

    public SidExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
