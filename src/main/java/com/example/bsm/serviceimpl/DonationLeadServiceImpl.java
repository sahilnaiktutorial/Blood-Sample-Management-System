package com.example.bsm.serviceimpl;

import com.example.bsm.entity.DonationLead;
import com.example.bsm.entity.DonationRequest;
import com.example.bsm.entity.User;
import com.example.bsm.exception.BloodBankNotFoundByIdException;
import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.repository.DonationLeadRepository;
import com.example.bsm.repository.DonationRequestRepository;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.request.DonationLeadRequest;
import com.example.bsm.response.DonationLeadResponse;
import com.example.bsm.service.DonationLeadService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class DonationLeadServiceImpl implements DonationLeadService {

    private UserRepository userRepository;

    private DonationLeadRepository donationLeadRepository;

    private DonationRequestRepository donationRequestRepository;

    private DonationLead mapToDonationLead(DonationLeadRequest donationLeadRequest, DonationLead donationLead) {

       donationLead.setDate(LocalDate.now());
       donationLead.setTime(LocalTime.now());
       return donationLead;
    }

    private DonationLeadResponse getDonationLeadResponse(DonationLead donationLead) {
        return DonationLeadResponse.builder()
                .date(donationLead.getDate())
                .time(donationLead.getTime())
                .leadId(donationLead.getLeadId())
                .build();
    }
    @Override
    public DonationLeadResponse createDonationLead(DonationLeadRequest donationLeadRequest, int userId, int requestId) {
        DonationLead donationLead = mapToDonationLead(donationLeadRequest,new DonationLead());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BloodBankNotFoundByIdException("Fail to find User"));

        DonationRequest donationRequest = donationRequestRepository.findById(requestId)
                .orElseThrow(() -> new BloodBankNotFoundByIdException("Fail to find DonationRequest"));

        donationLead.setUser(user);

        donationLeadRepository.save(donationLead);

        donationRequest.setDonationLead(donationLead);

        return getDonationLeadResponse(donationLead);
    }

    @Override
    public DonationLeadResponse findDonationLeadById(int leadId) {
        DonationLead donationLead  = donationLeadRepository.findById(leadId)
                .orElseThrow(() -> new BloodBankNotFoundByIdException("Fail to find DonationRequest"));
        return getDonationLeadResponse(donationLead);
    }
}
