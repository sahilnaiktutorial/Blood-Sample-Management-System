package com.example.bsm.controller;

import com.example.bsm.entity.Hospital;
import com.example.bsm.request.HospitalRequest;
import com.example.bsm.request.UserRequest;
import com.example.bsm.response.HospitalResponse;
import com.example.bsm.response.UserResponse;
import com.example.bsm.service.HospitalService;
import com.example.bsm.service.UserService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class HospitalController {

    private HospitalService hospitalService;

    private RestResponseBuilder restResponseBuilder;



    @GetMapping("/hospitals/{hospitalId}")
    public ResponseEntity<ResponseStructure<HospitalResponse>> findHospitalById(@PathVariable int hospitalId){
        HospitalResponse hospitalFound = hospitalService.findHospitalById(hospitalId);
        return restResponseBuilder.success(HttpStatus.FOUND,"hospital found",hospitalFound);
    }

    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN')")
    @PutMapping("/hospitals/{hospitalId}")
    public ResponseEntity<ResponseStructure<HospitalResponse>> updateHospital(@PathVariable("hospitalId") int hospitalId,@RequestBody HospitalRequest hospitalRequest) {
        HospitalResponse hospitalUpdated=hospitalService.updateHospital(hospitalId,hospitalRequest);
        return restResponseBuilder.success(HttpStatus.ACCEPTED,"hospital updated",hospitalUpdated);
    }


    @PreAuthorize("hasAuthority('OWNER_ADMIN')")
    @PostMapping("/hospitals/{adminId}")
    public ResponseEntity<ResponseStructure<HospitalResponse>> registerHospital(@RequestBody HospitalRequest hospitalRequest,@PathVariable int adminId){
        HospitalResponse hospital = hospitalService.registerHospital(hospitalRequest,adminId);
        return restResponseBuilder.success(HttpStatus.CREATED,"hospital created",hospital);
    }
}
