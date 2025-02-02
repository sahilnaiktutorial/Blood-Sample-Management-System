package com.example.bsm.repository;

import com.example.bsm.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository  extends JpaRepository<Survey,Integer> {
}
