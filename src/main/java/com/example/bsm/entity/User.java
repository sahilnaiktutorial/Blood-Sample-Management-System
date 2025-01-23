package com.example.bsm.entity;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

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

}
