package com.example.bsm.repository;

import com.example.bsm.entity.BloodBank;
import com.example.bsm.entity.DonationRequest;
import com.example.bsm.enums.BloodGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BloodBankRepository extends JpaRepository<BloodBank,Integer> {

     List<BloodBank> findByAddress_CityIn(List<String> city);

  // public BloodBank findBloodBank_adminId(int adminId);

    Page<BloodBank> findByAddress_CityInAndSamples_BloodGroupIn(List<String> cities, List<BloodGroup> bloodGroups, Pageable pageable);

//    Optional<BloodBank>  findBydonationRequestList(DonationRequest donationRequest);

    Optional<BloodBank> findByDonationRequestList(DonationRequest donationRequest);

    //OR  Hospital findByDonationRequest_hospitalId(int hospitalId);
}
