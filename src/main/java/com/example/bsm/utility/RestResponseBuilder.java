package com.example.bsm.utility;

import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RestResponseBuilder {

    public  <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status,String message,T data){
        return ResponseEntity
                .status(status)
                .body(ResponseStructure.<T>builder()
                        .httpStatus(status)
                        .message(message)
                        .data(data)
                        .build());
    }

    public  <T> ResponseEntity<ErrorStructure<T>> error(HttpStatus status,String message,T rootcause){
        return ResponseEntity
                .status(status)
                .body(ErrorStructure.<T>builder() //TYPECASTING RETURNTYPE OF BUILDER <T>
                        .status(status.value())
                        .message(message)
                        .rootCause(rootcause)
                        .build());
    }

}
