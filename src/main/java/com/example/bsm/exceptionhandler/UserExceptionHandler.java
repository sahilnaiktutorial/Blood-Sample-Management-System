package com.example.bsm.exceptionhandler;

import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.utility.ErrorStructure;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class UserExceptionHandler {

    private final RestResponseBuilder restResponseBuilder;

    @ExceptionHandler(UserNotFoundByIdException.class)
    public ResponseEntity<ErrorStructure> handleUserNotFoundById(UserNotFoundByIdException ex){
        return restResponseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage(),"user not found");
    }
}
