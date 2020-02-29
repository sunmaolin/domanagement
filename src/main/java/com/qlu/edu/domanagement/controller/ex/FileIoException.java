package com.qlu.edu.domanagement.controller.ex;

public class FileIoException extends UploadException {
    public FileIoException() {
    }

    public FileIoException(String message) {
        super(message);
    }

    public FileIoException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileIoException(Throwable cause) {
        super(cause);
    }

    public FileIoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
