package com.example.bsm.service;

import com.example.bsm.request.DonationLeadRequest;
import com.example.bsm.response.DonationLeadResponse;

public interface DonationLeadService {
    DonationLeadResponse createDonationLead(DonationLeadRequest donationLeadRequest, int userId, int requestId);

    DonationLeadResponse findDonationLeadById(int leadId);
}
