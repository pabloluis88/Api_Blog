package com.api.blog.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String mensagemDeErro = "O valor '" + ex.getValue() + "' não pode ser convertido para o tipo esperado. " +
                                "Certifique-se de que o formato do ID está correto (UUID).";
        return new ResponseEntity<>(mensagemDeErro, HttpStatus.BAD_REQUEST);
    }
}