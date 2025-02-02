package com.example.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankId;

    private LocalDate date;
    private LocalTime time;

    @OneToMany(mappedBy = "donation")
    private List<DonationRequest> donationRequestList;

    @ManyToOne
    private User user;


}
