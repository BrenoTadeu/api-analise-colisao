package com.project.estimate_value.exceptions;

import com.project.estimate_value.DTO.Errors.ErrorReponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorReponse> handleRuntime(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorReponse(ex.getMessage(), 500));
        }
}
