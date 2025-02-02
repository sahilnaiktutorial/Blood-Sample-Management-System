package com.example.bsm.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospitalRequest {

    @NotNull(message = "user cannot be null")
    @NotBlank(message = "user cannot be blank")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z_]{2,14}$",message = "Invalid username! Username must be 3 to 15 characters long, start with a letter, and can only contain letters, numbers, and underscores.")
    private String name;
}
