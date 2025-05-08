package com.studyhub.sth.libs.controller;

import com.studyhub.sth.libs.application.ServiceResponse;
import com.studyhub.sth.libs.controller.response.ErrorResponse;
import com.studyhub.sth.libs.controller.response.ResponseError;
import com.studyhub.sth.libs.controller.response.SuccessResponse;
import com.studyhub.sth.libs.core.errors.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class ControllerBase {
    public<T> ResponseEntity<SuccessResponse<T>> ok(T data, String message) {
        return ResponseEntity.ok(new SuccessResponse<T>(HttpStatus.OK, message, data));
    }

    public<T> ResponseEntity<SuccessResponse<T>> ok(T data) {
        return ResponseEntity.ok(new SuccessResponse<T>(HttpStatus.OK, "", data));
    }

    public<T> ResponseEntity<SuccessResponse<T>> created(T data, String message) {
        return new ResponseEntity<SuccessResponse<T>>(new SuccessResponse<T>(HttpStatus.CREATED, message, data), HttpStatus.CREATED);
    }

    public<T> ResponseEntity<SuccessResponse<T>> created(T data) {
        return new ResponseEntity<SuccessResponse<T>>(new SuccessResponse<T>(HttpStatus.CREATED, "", data), HttpStatus.CREATED);
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity noContent() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<ErrorResponse> badRequest(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErrorResponse> unauthorized() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<ErrorResponse> forbidden() {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<ErrorResponse> notFound() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public<T> ResponseEntity<?> internalServerError() {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor, tente novamente mais tarde!");
        return badRequest(errorResponse);
    }

    public<T> ResponseEntity<?> customResponse(T data) {
        if (data == null) return noContent();

        return ok(data);
    }

    public<T> ResponseEntity<?> customResponse(T data, String message) {
        if (data == null) return noContent();

        return ok(data, message);
    }

    public<T> ResponseEntity<?> customResponse(List<ResponseError> errors, String message) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, message);
        errorResponse.addMessages(errors);
        return badRequest(errorResponse);
    }

    public<T> ResponseEntity<?> customResponse(ResponseError error, String message) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, message);
        errorResponse.addMessage(error);
        return badRequest(errorResponse);
    }

    public<T> ResponseEntity<?> customResponse(ServiceResponse<?> data) {
        if (!data.getValidationErrors().isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "");
            errorResponse.addMessages(data.getValidationErrors().stream().map(validationError -> new ResponseError("STH-00", validationError.getError(), validationError.getDescription())).toList());

            return badRequest(errorResponse);
        }

        if (data.getResponse() == null) return noContent();

        return ok(data);
    }

    public ResponseEntity<?> customResponse(List<ValidationError> data) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "");
        errorResponse.addMessages(data.stream().map(validationError -> new ResponseError("STH-00", validationError.getError(), validationError.getDescription())).toList());

        return badRequest(errorResponse);
    }
}
