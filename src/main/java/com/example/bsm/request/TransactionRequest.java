package com.example.bsm.request;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.TransactionType;
import jakarta.persistence.Entity;
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
public class TransactionRequest {
    private LocalDate date;

    private LocalTime time;

    private int noOfUnits;

    private TransactionType transactionType;

    private BloodGroup bloodGroup;

}
