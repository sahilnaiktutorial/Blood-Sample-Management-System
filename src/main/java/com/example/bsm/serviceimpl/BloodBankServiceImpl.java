package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Address;
import com.example.bsm.entity.Admin;
import com.example.bsm.entity.BloodBank;
import com.example.bsm.entity.Sample;
import com.example.bsm.enums.BloodGroup;
import com.example.bsm.exception.BloodBankNotFoundByIdException;
import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.BloodBankRepository;
import com.example.bsm.request.BloodBankRequest;
import com.example.bsm.response.AddressResponse;
import com.example.bsm.response.BloodBankPageResponse;
import com.example.bsm.response.BloodBankResponse;
import com.example.bsm.response.SampleResponse;
import com.example.bsm.service.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BloodBankServiceImpl implements BloodBankService {

    @Autowired
    private BloodBankRepository bloodBankRepository;

    @Autowired
    private AdminRepository adminRepository;

    private BloodBank mapToBloodBank(BloodBankRequest bloodBankRequest, BloodBank bloodBank) {

        bloodBank.setName(bloodBankRequest.getName());
        bloodBank.setEnergencyUnitCount(bloodBankRequest.getEnergencyUnitCount());
        return bloodBank;
    }

    private BloodBankResponse getBloodBankResponse(BloodBank bloodBank) {
        return BloodBankResponse.builder()
                .bankId(bloodBank.getBankId())
                .name(bloodBank.getName())
                .energencyUnitCount(bloodBank.getEnergencyUnitCount())
                .build();
    }



    @Override
    public BloodBankResponse findBloodBankById(int bankId) {


        BloodBank bloodBank = bloodBankRepository.findById(bankId)
                .orElseThrow(() -> new BloodBankNotFoundByIdException("Fail to find BloodBank"));
        return this.getBloodBankResponse(bloodBank);
    }

    @Override
    public BloodBankResponse updateBloodBank(BloodBankRequest bloodBankRequest, int bankId) {
        Optional<BloodBank> optional = bloodBankRepository.findById(bankId);

        if (optional.isEmpty())
            throw new BloodBankNotFoundByIdException("Fail to update BloodBank with ID: " + bankId);

        BloodBank bloodBank = this.mapToBloodBank(bloodBankRequest,optional.get());
        BloodBank updatedbloodBank= bloodBankRepository.save(bloodBank);
        return this.getBloodBankResponse(updatedbloodBank);
    }

    @Override
    public BloodBankResponse registerBloodBank(BloodBankRequest bloodBankRequest, int adminId) {
        Optional<Admin> optional=adminRepository.findById(adminId);
        if (optional.isEmpty()){
            throw new UserNotFoundByIdException("failed to find Admin");
        }
        Admin admin=optional.get();
        List<Admin> adminList=new ArrayList<>();
        adminList.add(admin);

        BloodBank bloodBank = BloodBank.builder()
                .adminList(adminList)
                .energencyUnitCount(bloodBankRequest.getEnergencyUnitCount())
                .name(bloodBankRequest.getName())
                .build();


        adminRepository.save(admin);
        bloodBank= bloodBankRepository.save(bloodBank);
        return this.getBloodBankResponse(bloodBank);
    }

    @Override
    public Page<BloodBankPageResponse> findAllBloodBankByCity(
            List<String> city,
            List<BloodGroup> bloodGroups,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        Page<BloodBank> bloodBanks = bloodBankRepository.findByAddress_CityInAndSamples_BloodGroupIn(
                city,
                bloodGroups,
                pageable
        );

        if (bloodBanks.isEmpty()) {
            throw new BloodBankNotFoundByIdException("No blood banks found in the provided cities and blood groups");
        }

        return bloodBanks.map(bloodBank -> mapToBloodBankPageResponse(bloodBank, bloodGroups));
    }

    private BloodBankPageResponse mapToBloodBankPageResponse(BloodBank bloodBank, List<BloodGroup> requestedBloodGroups) {
        return BloodBankPageResponse.builder()
                .bankId(bloodBank.getBankId())
                .name(bloodBank.getName())
                .address(mapToAddressResponse(bloodBank.getAddress()))
                .samples(bloodBank.getSamples().stream()
                        .filter(sample -> requestedBloodGroups.contains(sample.getBloodGroup()))
                        .map(this::mapToSampleResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    private AddressResponse mapToAddressResponse(Address address) {
        return AddressResponse.builder()
                .addressId(address.getAddressId())
                .addressLine(address.getAddressLine())
                .landMark(address.getLandMark())
                .area(address.getArea())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .pincode(address.getPincode())
                .build();
    }

    private SampleResponse mapToSampleResponse(Sample sample) {
        return SampleResponse.builder()
                .sampleId(sample.getSampleId())
                .bloodGroup(sample.getBloodGroup())
                .quantity(sample.getQuantity())
                .availability(sample.isAvailability())
                .emergencyUnit(sample.getEmergencyUnits())
                .availableUnit(sample.getAvailableUnits())
                .build();
    }





}
