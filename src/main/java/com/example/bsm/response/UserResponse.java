package com.example.bsm.response;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private int userId;
    private String username;
    private BloodGroup bloodGroup;
    private Date lastDenotedDate;
    private int age;
    private Gender gender ;
    private String availableCity;
    private boolean verified;
}
