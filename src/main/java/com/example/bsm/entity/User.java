package com.example.bsm.entity;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.Gender;
import com.example.bsm.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class User {



     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int userId;

    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private BloodGroup bloodGroup;
    private Date lastDenotedDate;
    private int age;
    private Gender gender ;
    private String availableCity;
    private boolean verified;
    private Role role;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private Admin admin;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transaction;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Survey> surveys;

    @OneToMany(mappedBy = "user")
    private List<DonationLead> donationLeadList;


    @OneToMany
    private List<Donation> donationList;
}
