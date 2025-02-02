package com.example.bsm.repository;

import com.example.bsm.entity.DonationRequest;
import com.example.bsm.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository  extends JpaRepository<Hospital,Integer> {
//    Optional<Hospital> findByDonationRequestList(DonationRequest donationRequest);


    Hospital findByDonationRequestList(DonationRequest donationRequest);



//@Query("SELECT h FROM Hospital h JOIN h.donationRequestList d WHERE d = :donationRequest")
//Optional<Hospital> findHospitalByDonationRequest(@Param("donationRequest") DonationRequest donationRequest);

}
