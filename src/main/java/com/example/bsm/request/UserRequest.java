package com.example.bsm.request;


import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
//@NotNull(message = "user cannot be null")
//@NotBlank(message = "user cannot be blank")
//@Pattern(regexp = "^[a-zA-Z][a-zA-Z_]{2,14}$",message = "Invalid username! Username must be 3 to 15 characters long, start with a letter, and can only contain letters, numbers, and underscores.")
    private String username;

//@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n",message = "Invalid email! Please enter a valid email address (e.g., example@domain.com).")
//    @NotNull(message = "email cannot be null")
//    @NotBlank(message = "email cannot be blank")
    private String email;

//@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$\n",message = "Invalid password! Password must be at least 8 characters long, \"\n" +
//        "                    + \"include at least one uppercase letter, one lowercase letter, one number, and one special character.")
//    @NotNull(message = "password cannot be null")
//    @NotBlank(message = "password cannot be blank")
    private String password;

//@Pattern(regexp = "^(\\+?\\d{1,3})?[-.\\s]?\\d{10}$\n",message = "Invalid phone number! Please enter a valid 10-digit phone number. \"\n" +
//        "                    + \"It can optionally start with a country code (e.g., +91).")
//    @NotNull(message = "phonenumber cannot be null")
//    @NotBlank(message = "phonenumber cannot be blank")
    private String phoneNumber;

    @Min(1)
    @Max(100)
    private int age;

    private Gender gender ;

//    @Pattern(regexp = "^[a-zA-Z]+([-\\s][a-zA-Z]+)*$\n",message = "Invalid city name! City name should contain only letters, \"\n" +
//            "+ \"optional spaces or hyphens, and must be at least 2 characters long.")
//    @NotNull(message = "city cannot be null")
//    @NotBlank(message = "city cannot be blank")
    private String availableCity;

    private BloodGroup bloodGroup;

    private Date lastDenotedDate;
}
