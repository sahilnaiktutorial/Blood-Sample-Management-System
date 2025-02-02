package com.example.bsm.service;

import com.example.bsm.request.SurveyRequest;
import com.example.bsm.response.SurveyResponse;
import jakarta.validation.Valid;

public interface SurveyService {
    SurveyResponse addSurvey(@Valid SurveyRequest surveyRequest);

    SurveyResponse findSurveyById(int surveyId);

    SurveyResponse updateSurvey(SurveyRequest surveyRequest,int surveyId);
}
