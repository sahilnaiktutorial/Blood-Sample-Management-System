package com.example.bsm.service;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.request.DonationRequests;
import com.example.bsm.response.DonationResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface DonationService {
    DonationResponse addDonation(@Valid DonationRequests donationRequest, int requestId);

    List<DonationResponse> findAllDonations(int requestId);

    DonationResponse updateDonation(DonationRequests donationRequest,int donationId);
}
