package com.example.bsm.controller;

import com.example.bsm.request.DonationRequestDto;
import com.example.bsm.response.DonationRequestResponse;
import com.example.bsm.service.DonationRequestService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class DonationRequestController {

    private DonationRequestService donationRequestService;
    private RestResponseBuilder restResponseBuilder;

    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN')")
    @PostMapping("/donationReq/{hospitalId}")
    public ResponseEntity<ResponseStructure<DonationRequestResponse>> registerHospitalDonationRequest(@RequestBody DonationRequestDto donationRequestdto, @PathVariable int hospitalId) {
        DonationRequestResponse donationRequest = donationRequestService.registerHospitalDonationRequest(donationRequestdto, hospitalId);
        return restResponseBuilder.success(HttpStatus.CREATED, "donation created", donationRequest);
    }

    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN')")
    @PostMapping("/donationRequest/{bankId}")
    public ResponseEntity<ResponseStructure<DonationRequestResponse>> registerBloodBankDonationRequest(@RequestBody DonationRequestDto donationRequestdto, @PathVariable int bankId) {
        DonationRequestResponse donationRequest = donationRequestService.registerBloodBankDonationRequest(donationRequestdto, bankId);
        return restResponseBuilder.success(HttpStatus.CREATED, "donation created", donationRequest);
    }
}
