package com.example.bsm.controller;

import com.example.bsm.request.TransactionRequest;
import com.example.bsm.response.TransactionResponse;
import com.example.bsm.service.TransactionService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

public class TransactionController {

    private RestResponseBuilder restResponseBuilder;

private TransactionService transactionService;



    @PreAuthorize("hasAuthority('OWNER_ADMIN')")
    @PutMapping("/transaction/{hospitalId}")
    public ResponseEntity<ResponseStructure<TransactionResponse>> checkTransaction(@RequestBody TransactionRequest transactionRequest,@PathVariable int hospitalId,@PathVariable int userId) {
        TransactionResponse transactionResponse = transactionService.checkTransaction(transactionRequest, hospitalId, userId);
        return restResponseBuilder.success(HttpStatus.CREATED, "transaction created",transactionResponse);
    }
}
