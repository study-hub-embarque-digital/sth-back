package com.studyhub.sth.api.filters;

import com.studyhub.sth.libs.controller.ControllerBase;
import com.studyhub.sth.libs.core.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerFilter extends ControllerBase {
    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<?> validationExceptionHandler(ValidationException exception) {

        return this.customResponse(exception.getValidationErrors());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> generalExceptionHandler(Exception exception) {
        return new ResponseEntity<>(new Retorno(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<String> handleMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body("The requested media type is not supported. Supported types: " + ex.getMessage());
    }
}