package com.studyhub.sth.api.filters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerFilter {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Retorno> badRequestExceptionHandler(Exception exception) {
        return new ResponseEntity<>(new Retorno(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private class Retorno {
        private String message;
        private List<String> erros;

        public Retorno(String message) {
            this.message = message;
            this.erros = new ArrayList<>();
        }
    }
}