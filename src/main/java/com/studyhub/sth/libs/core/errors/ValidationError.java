package com.studyhub.sth.libs.core.errors;

import lombok.Getter;

@Getter
public class ValidationError {
    private final String error;
    private String description;

    public ValidationError(String error, String description) {
        this.error = error;
        this.description = description;
    }

    public ValidationError(String error) {
        this.error = error;
    }
}
