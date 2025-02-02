package com.example.bsm.controller;


import com.example.bsm.request.DonationLeadRequest;
import com.example.bsm.response.DonationLeadResponse;
import com.example.bsm.service.DonationLeadService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class DonationLeadController {
    private RestResponseBuilder restResponseBuilder;

    private DonationLeadService donationLeadService;

    @PostMapping("donationLead")
    public ResponseEntity<ResponseStructure<DonationLeadResponse>> createDonationLead(@RequestBody DonationLeadRequest donationLeadRequest, @RequestParam int userId, @RequestParam int requestId){
    DonationLeadResponse donationLeadResponse = donationLeadService.createDonationLead(donationLeadRequest,userId,requestId);
        return restResponseBuilder.success(HttpStatus.CREATED,"DonationLead Created",donationLeadResponse);
    }

    @PutMapping("/donationLead/{leadId}")
    public ResponseEntity<ResponseStructure<DonationLeadResponse>> findDonationLeadById(@RequestParam int leadId){
        DonationLeadResponse donationLeadResponse = donationLeadService.findDonationLeadById(leadId);
        return restResponseBuilder.success(HttpStatus.CREATED,"DonationLead Created",donationLeadResponse);
    }

}
