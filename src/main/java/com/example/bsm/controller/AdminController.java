package com.example.bsm.controller;

import com.example.bsm.response.AdminResponse;
import com.example.bsm.service.AdminService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;

    private RestResponseBuilder restResponseBuilder;

    @PostMapping("/Admins/{userId}")
    public ResponseEntity<ResponseStructure<AdminResponse>> addAdmin(@PathVariable int userId){
        AdminResponse admin = adminService.addAdmin(userId);
        return restResponseBuilder.success(HttpStatus.CREATED,"user created",admin);
    }


}
