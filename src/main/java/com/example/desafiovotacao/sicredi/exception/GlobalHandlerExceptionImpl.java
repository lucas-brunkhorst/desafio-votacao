package com.example.desafiovotacao.sicredi.exception;

import com.example.desafiovotacao.sicredi.exception.custom.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerExceptionImpl {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleNotFoundException(BadRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
