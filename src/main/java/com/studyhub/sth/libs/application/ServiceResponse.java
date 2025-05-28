package com.studyhub.sth.libs.application;

import com.studyhub.sth.libs.core.errors.ValidationError;
import com.studyhub.sth.libs.core.exceptions.ValidationException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ServiceResponse<T> {
    private T response;
    private final List<ValidationError> validationErrors;

    public ServiceResponse() {
        this.validationErrors = new ArrayList<>();
    }

    public ServiceResponse<T> fail(String error, String detail) {
        this.validationErrors.add(new ValidationError(error, detail));
        return this;
    }

    public ServiceResponse<T> fail(String error) {
        this.validationErrors.add(new ValidationError(error));
        return this;
    }

    public ServiceResponse<T> success(T data) {
        if (this.validationErrors.isEmpty()) {
            this.response = data;
            return this;
        }

        throw new ValidationException("Houveram problemas de validação!", validationErrors);
    }
}
