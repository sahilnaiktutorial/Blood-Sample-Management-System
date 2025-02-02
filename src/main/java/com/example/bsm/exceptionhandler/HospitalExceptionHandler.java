package com.example.bsm.exceptionhandler;


import com.example.bsm.exception.HospitalNotFoundByIdException;
import com.example.bsm.utility.ErrorStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class HospitalExceptionHandler {
    private final RestResponseBuilder restResponseBuilder;

    @ExceptionHandler(HospitalNotFoundByIdException.class)
    public ResponseEntity<ErrorStructure<String>> handleHospitalNotFoundById(HospitalNotFoundByIdException ex){
        return restResponseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage(),"Hospital not found");
    }
}
