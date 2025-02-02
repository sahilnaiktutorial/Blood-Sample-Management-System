package com.example.bsm.response;

import com.example.bsm.enums.BloodGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequestResponse {

    private int requestId;

    private LocalDate date;

    private LocalTime time;

    private List<BloodGroup> bloodGroupList;

    private  List<String> cities;
}
