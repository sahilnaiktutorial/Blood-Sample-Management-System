package com.example.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BloodBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankId;
    private String name;
    private int energencyUnitCount;

    @OneToMany(mappedBy = "bloodBank",fetch = FetchType.EAGER)
    private List<Admin> adminList;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "bloodBank", fetch = FetchType.LAZY)
    private List<Sample> samples;

    @OneToMany
    private List<DonationRequest> donationRequestList;

}
