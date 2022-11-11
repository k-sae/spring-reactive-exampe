package com.example.springreactiveprototype.exception.exception;

import com.example.springreactiveprototype.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException e) {
        // log exception
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), "RESOURCE_NOT_FOUND"), HttpStatus.NOT_FOUND);
    }

}
