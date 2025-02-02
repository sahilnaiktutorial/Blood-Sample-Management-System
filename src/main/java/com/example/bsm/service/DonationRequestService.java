package com.example.bsm.service;

import com.example.bsm.request.DonationRequestDto;
import com.example.bsm.response.DonationRequestResponse;

public interface DonationRequestService {
    

    DonationRequestResponse registerHospitalDonationRequest(DonationRequestDto donationRequestdto, int hospitalId);

    DonationRequestResponse registerBloodBankDonationRequest(DonationRequestDto donationRequestdto, int bankId);
}
