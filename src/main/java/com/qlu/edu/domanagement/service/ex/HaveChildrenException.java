package com.qlu.edu.domanagement.service.ex;

public class HaveChildrenException extends ServiceException{
    public HaveChildrenException() {
    }

    public HaveChildrenException(String message) {
        super(message);
    }

    public HaveChildrenException(String message, Throwable cause) {
        super(message, cause);
    }

    public HaveChildrenException(Throwable cause) {
        super(cause);
    }

    public HaveChildrenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
