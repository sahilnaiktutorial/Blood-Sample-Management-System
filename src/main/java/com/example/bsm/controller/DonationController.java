package com.example.bsm.controller;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.request.DonationRequests;
import com.example.bsm.response.DonationResponse;
import com.example.bsm.service.DonationService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DonationController {

   private DonationService donationService;

    private RestResponseBuilder restResponseBuilder;

    @PostMapping("/donations/{userId}")
    public ResponseEntity<ResponseStructure<DonationResponse>> addDonations(@RequestBody @Valid DonationRequests donationRequest, @PathVariable int requestId) {
        DonationResponse donationResponse = donationService.addDonation(donationRequest,requestId);
        return restResponseBuilder.success(HttpStatus.CREATED, "Donation created", donationResponse);
    }

    @GetMapping("/donations/{requestId}")
    public ResponseEntity<ResponseStructure<List<DonationResponse>>> findAllDonations(@RequestParam int requestId){
        List<DonationResponse> donationResponseList = donationService.findAllDonations(requestId);
        return restResponseBuilder.success(HttpStatus.CREATED, "Donation found", donationResponseList);
    }

    @PutMapping("donations/{donationId}")
    public ResponseEntity<ResponseStructure<DonationResponse>> updateDonation(@PathVariable() int donationId,@RequestBody @Valid DonationRequests donationRequest) {
        DonationResponse donationResponse = donationService.updateDonation(donationRequest,donationId);
        return restResponseBuilder.success(HttpStatus.CREATED, "user created", donationResponse);
    }
}
