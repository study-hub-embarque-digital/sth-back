package com.studyhub.sth.libs.controller.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SuccessResponse<T> extends Response {
    private final T data;

    public SuccessResponse(HttpStatus statusCode, String message, T data) {
        super(statusCode, message);
        this.data = data;
    }

    public SuccessResponse(HttpStatus statusCode, String message) {
        super(statusCode, message);
        this.data = null;
    }
}
