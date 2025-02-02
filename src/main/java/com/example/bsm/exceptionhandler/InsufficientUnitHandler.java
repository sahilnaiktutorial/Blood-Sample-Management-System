package com.example.bsm.exceptionhandler;

import com.example.bsm.exception.InsufficientUnitException;
import com.example.bsm.utility.ErrorStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class InsufficientUnitHandler {
    private final RestResponseBuilder responseBuilder;

    @ExceptionHandler(InsufficientUnitException.class)
    public<T> ResponseEntity<ErrorStructure<String>> handleUserNotFoundById(InsufficientUnitException ex){
        return responseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "EmergencyUnits not available");
    }

}
