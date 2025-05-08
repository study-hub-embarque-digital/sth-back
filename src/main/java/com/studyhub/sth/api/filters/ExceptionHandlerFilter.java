package com.studyhub.sth.api.filters;

import com.studyhub.sth.libs.controller.ControllerBase;
import com.studyhub.sth.libs.core.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Retorno> badRequestExceptionHandler(Exception exception) {
//        return new ResponseEntity<>(new Retorno(exception.getMessage()), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Retorno> handleMissingParams(MissingServletRequestParameterException ex) {
        String nomeParametro = ex.getParameterName();
        String mensagem = "O parâmetro obrigatório '" + nomeParametro + "' está faltando.";
        Retorno retorno = new Retorno(mensagem);
        retorno.getErros().add("Parâmetro ausente: " + nomeParametro);
        return new ResponseEntity<>(retorno, HttpStatus.BAD_REQUEST);
    }

    public static class Retorno {
        private String message;
        private List<String> erros;

        public Retorno(String message) {
            this.message = message;
            this.erros = new ArrayList<>();
        }

        public String getMessage() {
            return message;
        }

        public List<String> getErros() {
            return erros;
        }
    }
}