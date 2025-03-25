package org.ivanov.andrey.diet;

public class ErrorResponse {

    private ErrorCode errorCode;
    private String message;

    public ErrorResponse(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
