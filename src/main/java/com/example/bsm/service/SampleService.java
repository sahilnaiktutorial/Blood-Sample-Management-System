package com.example.bsm.service;

import com.example.bsm.request.SampleRequest;
import com.example.bsm.response.SampleResponse;

public interface SampleService {
    SampleResponse findSampleById(int sampleId);

    SampleResponse updateSample(int sampleId, SampleRequest sampleRequest);

    SampleResponse addSample(SampleRequest sampleRequest);

    SampleResponse registerSample(SampleRequest sampleRequest, int bankId);
}
