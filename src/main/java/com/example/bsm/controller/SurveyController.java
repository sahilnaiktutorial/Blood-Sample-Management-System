package com.example.bsm.controller;

import com.example.bsm.entity.Survey;
import com.example.bsm.request.SurveyRequest;
import com.example.bsm.request.UserRequest;
import com.example.bsm.response.SurveyResponse;
import com.example.bsm.response.UserResponse;
import com.example.bsm.service.SurveyService;
import com.example.bsm.service.UserService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SurveyController {
    private SurveyService surveyService;
    private RestResponseBuilder restResponseBuilder;


    @PostMapping("/surveys")
    public ResponseEntity<ResponseStructure<SurveyResponse>> addSurvey(@RequestBody @Valid SurveyRequest surveyRequest) {
        SurveyResponse surveyResponse = surveyService.addSurvey(surveyRequest);
        return restResponseBuilder.success(HttpStatus.CREATED, "Survey created", surveyResponse);
    }

    @GetMapping("/surveys/{surveyId}")
    public ResponseEntity<ResponseStructure<SurveyResponse>> findSurveyById(int surveyId) {
        SurveyResponse surveyResponse = surveyService.findSurveyById(surveyId);
        return restResponseBuilder.success(HttpStatus.FOUND, "user found", surveyResponse);
    }

    @PutMapping("/surveys/{surveyId}")
    public ResponseEntity<ResponseStructure<SurveyResponse>> updateSurvey(@RequestBody SurveyRequest surveyRequest,int surveyId) {
        SurveyResponse surveyResponse = surveyService.updateSurvey(surveyRequest,surveyId);
        return restResponseBuilder.success(HttpStatus.ACCEPTED, "user updated", surveyResponse);
    }
}
