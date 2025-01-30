package com.example.bsm.repository;

import com.example.bsm.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository  extends JpaRepository<Hospital,Integer> {
}
