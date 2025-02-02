package com.example.bsm.service;

import com.example.bsm.request.TransactionRequest;
import com.example.bsm.response.TransactionResponse;
import jakarta.validation.Valid;

public interface TransactionService {

    TransactionResponse checkTransaction(TransactionRequest transactionRequest,int hospitalId, int userId);
}
