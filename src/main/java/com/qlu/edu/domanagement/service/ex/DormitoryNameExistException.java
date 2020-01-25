package com.qlu.edu.domanagement.service.ex;

public class DormitoryNameExistException extends ServiceException {
    public DormitoryNameExistException() {
    }

    public DormitoryNameExistException(String message) {
        super(message);
    }

    public DormitoryNameExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DormitoryNameExistException(Throwable cause) {
        super(cause);
    }

    public DormitoryNameExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
