package com.example.bsm.repository;

import com.example.bsm.entity.Donation;
import com.example.bsm.entity.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation,Integer> {
    List<Donation> findByDonationRequestList(DonationRequest donationRequest);

}
