package com.example.bsm.exceptionhandler;

import com.example.bsm.utility.ErrorStructure;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class FieldErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<ObjectError> objectErrors = ex.getAllErrors();

        List<FieldErrorStructure> errors = new ArrayList<>();

        for (ObjectError objectError: objectErrors){

            FieldError error =(FieldError) objectError;

            errors.add(FieldErrorStructure.builder()
                            .field(error.getField())
                            .message(error.getDefaultMessage())
                            .rejectedValue(error.getRejectedValue())
                    .build());

        }

        ErrorStructure<List<FieldErrorStructure>> error =new ErrorStructure<>();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Invalid input");
        error.setRootCause(errors);


        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldErrorStructure  {
        private  String field;
        private Object rejectedValue;
        private  String message;
    }
}




//            fieldErrorStructure.setMessage(error.getDefaultMessage());
//            fieldErrorStructure.setField(error.getField());
//            fieldErrorStructure.setRejectedValue(error.getRejectedValue());
//            FieldErrorStructure fieldErrorStructure = new FieldErrorStructure();