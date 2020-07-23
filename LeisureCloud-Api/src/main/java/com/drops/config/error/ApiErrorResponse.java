package com.drops.config.error;

import com.drops.config.error.ApiErrorResponse;


public class ApiErrorResponse {
    private int status;
    private int code;
    private String message;

    public ApiErrorResponse(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }


    public int getStatus() {
        return this.status;
    }


    public int getCode() {
        return this.code;
    }


    public String getMessage() {
        return this.message;
    }


    public String toString() {
        return "ApiErrorResponse{status=" + this.status + ", code=" + this.code + ", message=" + this.message + '}';
    }
}