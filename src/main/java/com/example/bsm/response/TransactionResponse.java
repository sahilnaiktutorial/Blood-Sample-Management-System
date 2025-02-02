package com.example.bsm.response;


import com.example.bsm.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private LocalDate date;

    private LocalTime time;

    private int noOfUnits;

    private TransactionType transactionType;

    private int transactionId;
}


