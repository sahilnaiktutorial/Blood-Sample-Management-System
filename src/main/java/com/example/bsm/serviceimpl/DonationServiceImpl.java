package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Donation;
import com.example.bsm.entity.User;
import com.example.bsm.entity.DonationRequest;
import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.repository.DonationRepository;
import com.example.bsm.repository.DonationRequestRepository;

import com.example.bsm.request.DonationRequests;
import com.example.bsm.response.DonationResponse;
import com.example.bsm.security.AuthUtil;
import com.example.bsm.service.DonationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationServiceImpl implements DonationService {

private DonationRepository donationRepository;
    private AuthUtil authUtil;

    private DonationRequestRepository donationRequestRepository;

    private  Donation mapToDonation(DonationRequests donationRequest, Donation donation) {
donation.setTime(LocalTime.now());
donation.setDate(LocalDate.now());
        return donation;
    }



    private DonationResponse getDonationResponse(Donation donation) {
        return DonationResponse.builder()
                .date(donation.getDate())
                .time(donation.getTime())
                .build();
    }


    @Override
    public DonationResponse addDonation(DonationRequests donationRequest, int requestId) {
        User user = authUtil.getCurrentUser();
        DonationRequest donationRequest1 = donationRequestRepository.findById(requestId)
                .orElseThrow(() -> new UserNotFoundByIdException("Fail to find user"));
        Donation donation = mapToDonation(donationRequest,new Donation());
donation.setUser(user);
        donationRepository.save(donation);
        donationRequest1.setDonation(donation);


        return getDonationResponse(donation);
    }

    @Override
    public List<DonationResponse> findAllDonations(int requestId) {
        DonationRequest donationRequest = donationRequestRepository.findById(requestId)
                .orElseThrow(() -> new UserNotFoundByIdException("Fail to find user"));

        List<Donation> donationsList=donationRepository.findByDonationRequestList(donationRequest);
        return donationsList.stream()
                .map(donation -> DonationResponse.builder()
                        .date(donation.getDate())
                        .time(donation.getTime())
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public DonationResponse updateDonation(DonationRequests donationRequest, int donationId) {
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new UserNotFoundByIdException("Fail to find user"));
        Donation donation1 = this.mapToDonation(donationRequest,donation);
        donationRepository.save(donation1);
        return this.getDonationResponse(donation1);
    }
}
