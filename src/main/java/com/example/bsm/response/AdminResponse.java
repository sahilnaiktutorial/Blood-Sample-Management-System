package com.example.bsm.response;

import com.example.bsm.entity.Hospital;
import com.example.bsm.entity.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {

    private int adminId;
    private User user;
//    private Hospital hospital;
}
