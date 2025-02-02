package com.example.bsm.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BloodBankResponse {

    private int bankId;
    private String name;
    private int energencyUnitCount;
}
