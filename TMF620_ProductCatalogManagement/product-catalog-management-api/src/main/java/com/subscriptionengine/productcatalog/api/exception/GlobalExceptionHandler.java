package com.subscriptionengine.productcatalog.api.exception;

import org.openapitools.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> handleNotFound(ResourceNotFoundException ex) {
        Error error = new Error();
        error.setCode("ERR-404");
        error.setReason("Not Found");
        error.setMessage(ex.getMessage());
        error.setStatus("404");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        
        Error error = new Error();
        error.setCode("ERR-400");
        error.setReason("Validation Failed");
        error.setMessage(message);
        error.setStatus("400");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGeneric(Exception ex) {
        Error error = new Error();
        error.setCode("ERR-500");
        error.setReason("Internal Server Error");
        error.setMessage(ex.getMessage());
        error.setStatus("500");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
