package com.example.bsm.entity;


import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.OrganizationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequest{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;

    private LocalDate date;

    private LocalTime time;

    private List<BloodGroup> bloodGroupList;

    @ManyToOne
    private Donation donation;

    @ManyToOne
    private DonationLead donationLead;

    private OrganizationType organizationType;

    private boolean requestCompleted;

    private  List<String> cities;


}
