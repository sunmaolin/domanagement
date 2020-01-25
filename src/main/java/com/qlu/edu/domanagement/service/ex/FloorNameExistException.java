package com.qlu.edu.domanagement.service.ex;

public class FloorNameExistException extends ServiceException {
    public FloorNameExistException() {
    }

    public FloorNameExistException(String message) {
        super(message);
    }

    public FloorNameExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public FloorNameExistException(Throwable cause) {
        super(cause);
    }

    public FloorNameExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
