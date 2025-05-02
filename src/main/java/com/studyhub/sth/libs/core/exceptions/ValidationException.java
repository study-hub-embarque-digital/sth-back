package com.studyhub.sth.libs.core.exceptions;

import com.studyhub.sth.libs.core.errors.ValidationError;
import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
  private final String code;
  private final List<ValidationError> validationErrors;

  public ValidationException(String message, List<ValidationError> validationErrors) {
    super(message);
    this.validationErrors = validationErrors;
    this.code = "STH-00";
  }
}
