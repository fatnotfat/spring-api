package com.fatnotfat.identity_service.exception;

public enum ErrorCode {

    USER_EXISTED(400,"User existed!"),
    USER_NOTFOUND(404,"User not found!"),
    INVALID_KEY(400,"Invalid message key!"),
    USERNAME_INVALID(400,"Username must at least 3 characters!"),
    PASSWORD_INVALID(400,"Password must at least 8 characters!"),
    PASSWORD_NULL(400,"Password cannot be null!"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
