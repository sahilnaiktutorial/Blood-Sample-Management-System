package com.example.bsm.request;


import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private int age;
    private Gender gender ;
    private String availableCity;
    private BloodGroup bloodGroup;
}
