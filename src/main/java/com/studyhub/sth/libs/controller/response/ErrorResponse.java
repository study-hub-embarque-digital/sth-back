package com.studyhub.sth.libs.controller.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse extends Response {
    private final List<ResponseError> errors;

    public ErrorResponse(HttpStatus statusCode, String message) {
        super(statusCode, message);
        this.errors = new ArrayList<>();
    }

    public void addMessage(ResponseError error) {
        this.errors.add(error);
    }

    public void addMessages(List<ResponseError> errors) {
        this.errors.addAll(errors);
    }
}
