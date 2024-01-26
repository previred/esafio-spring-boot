package com.test.previred.domain.model.common;

public class OperationException extends RuntimeException {
    public static final int HTTP_STATUS_BAD_REQUEST = 400;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    private final int httpStatus;

    public OperationException(String message) {
        super(message);
        this.httpStatus = HTTP_STATUS_BAD_REQUEST;
    }

    public OperationException(int httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public OperationException(String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = HTTP_STATUS_BAD_REQUEST;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
