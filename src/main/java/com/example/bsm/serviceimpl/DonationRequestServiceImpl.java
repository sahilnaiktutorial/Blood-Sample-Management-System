package com.example.bsm.serviceimpl;

import com.example.bsm.entity.BloodBank;
import com.example.bsm.entity.DonationRequest;
import com.example.bsm.entity.Hospital;
import com.example.bsm.enums.OrganizationType;
import com.example.bsm.exception.BloodBankNotFoundByIdException;
import com.example.bsm.exception.HospitalNotFoundByIdException;
import com.example.bsm.repository.BloodBankRepository;
import com.example.bsm.repository.DonationRequestRepository;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.request.DonationRequestDto;
import com.example.bsm.response.DonationRequestResponse;
import com.example.bsm.service.DonationRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class DonationRequestServiceImpl implements DonationRequestService {

    private final HospitalRepository hospitalRepository;
    private final DonationRequestRepository donationRequestRepository;
    private final BloodBankRepository bloodBankRepository;



    private DonationRequest mapToDonation(DonationRequestDto dto,DonationRequest donationRequest) {
       donationRequest.setDate(LocalDate.now());
       donationRequest.setTime(LocalTime.now());
       donationRequest.setBloodGroupList(dto.getBloodGroupList());
       donationRequest.setCities(dto.getCities());
       return donationRequest;
    }

    private DonationRequestResponse getDonationResponse(DonationRequest donationRequest) {
        return DonationRequestResponse.builder()
                .requestId(donationRequest.getRequestId())
                .date(donationRequest.getDate())
                .time(donationRequest.getTime())
                .bloodGroupList(donationRequest.getBloodGroupList())
                .cities(donationRequest.getCities())
                .build();
    }

    @Override
    public DonationRequestResponse registerHospitalDonationRequest(DonationRequestDto dto, int hospitalId) {
        DonationRequest donationRequest = mapToDonation(dto,new DonationRequest());
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundByIdException("Fail to find hospital"));

        List<DonationRequest> donationRequestList = hospital.getDonationRequestList();
        if (donationRequestList==null)
            donationRequestList = new ArrayList<>();
        donationRequest.setOrganizationType(OrganizationType.HOSPITAL);
        donationRequestRepository.save(donationRequest);
        donationRequestList.add(donationRequest);
        return getDonationResponse(donationRequest);
    }

    @Override
    public DonationRequestResponse registerBloodBankDonationRequest(DonationRequestDto dto, int bankId) {
        DonationRequest donationRequest = mapToDonation(dto,new DonationRequest());
        BloodBank bloodBank = bloodBankRepository.findById(bankId)
                .orElseThrow(() -> new BloodBankNotFoundByIdException("Fail to find BloodBank"));

        List<DonationRequest> donationRequestList = bloodBank.getDonationRequestList();
        if (donationRequestList==null)
            donationRequestList = new ArrayList<>();
        donationRequest.setOrganizationType(OrganizationType.BLOODBANK);
        donationRequestRepository.save(donationRequest);
        donationRequestList.add(donationRequest);
        return getDonationResponse(donationRequest);
    }
}