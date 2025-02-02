package com.example.bsm.repository;

import com.example.bsm.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample,Integer> {
}
