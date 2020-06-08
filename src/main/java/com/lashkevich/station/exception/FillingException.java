package com.lashkevich.station.exception;

public class FillingException extends RuntimeException {
    public FillingException() {
    }

    public FillingException(String message) {
        super(message);
    }

    public FillingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FillingException(Throwable cause) {
        super(cause);
    }

    public FillingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
