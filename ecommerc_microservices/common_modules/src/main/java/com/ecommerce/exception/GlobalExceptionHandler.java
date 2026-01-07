package com.ecommerce.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage(),
                        LocalDateTime.now()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Validation failed",
                        LocalDateTime.now()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        log.error("Exception:", ex);
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Internal Server Error",
                        LocalDateTime.now()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
