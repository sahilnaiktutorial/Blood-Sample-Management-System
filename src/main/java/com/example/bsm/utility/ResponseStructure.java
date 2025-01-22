package com.example.bsm.utility;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStructure<T> {

    private HttpStatus httpStatus;
    private String message;
    private T data;


}
