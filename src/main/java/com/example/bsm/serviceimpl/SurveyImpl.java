package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Survey;
import com.example.bsm.entity.User;
import com.example.bsm.exception.SurveyNotFoundByIdException;
import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.repository.SurveyRepository;
import com.example.bsm.request.SurveyRequest;
import com.example.bsm.response.SurveyResponse;
import com.example.bsm.security.AuthUtil;
import com.example.bsm.service.SurveyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SurveyImpl implements SurveyService {
    private AuthUtil authUtil;

    private SurveyRepository surveyRepository;

    private Survey mapToSurvey(SurveyRequest surveyRequest, Survey survey) {
    survey.setAdditives(surveyRequest.isAdditives());
    survey.setMedicines(surveyRequest.isMedicines());
    survey.setConsumedAlcohol(surveyRequest.isConsumedAlcohol());
    survey.setConsumedTobacco(surveyRequest.isConsumedTobacco());
    survey.setPreMedicalCondition(surveyRequest.isPreMedicalCondition());
        return survey;
    }



    private SurveyResponse getSurveyResponse(Survey survey) {
        return SurveyResponse.builder()
                .additives(survey.isAdditives())
                .consumedAlcohol(survey.isConsumedAlcohol())
                .consumedTobacco(survey.isConsumedTobacco())
                .medicines(survey.isMedicines())
                .preMedicalCondition(survey.isPreMedicalCondition())
                .build();
    }



    @Override
    public SurveyResponse addSurvey(SurveyRequest surveyRequest) {
        User user = authUtil.getCurrentUser();
        Survey survey = this.mapToSurvey(surveyRequest,new Survey());
        survey.setUser(user);
        surveyRepository.save(survey);
        return this.getSurveyResponse(survey);
    }

    @Override
    public SurveyResponse findSurveyById(int surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new SurveyNotFoundByIdException("Fail to find Survey"));
        return this.getSurveyResponse(survey);
    }

    @Override
    public SurveyResponse updateSurvey(SurveyRequest surveyRequest,int surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new SurveyNotFoundByIdException("Fail to find Survey"));
        Survey newSurvey= this.mapToSurvey(surveyRequest,survey);
        return this.getSurveyResponse(newSurvey);
    }
}
