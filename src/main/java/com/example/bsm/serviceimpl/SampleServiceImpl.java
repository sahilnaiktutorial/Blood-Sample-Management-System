package com.example.bsm.serviceimpl;

import com.example.bsm.entity.BloodBank;
import com.example.bsm.entity.Sample;
import com.example.bsm.exception.SampleNotFoundByIdException;
import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.repository.BloodBankRepository;
import com.example.bsm.repository.SampleRepository;
import com.example.bsm.request.SampleRequest;
import com.example.bsm.response.SampleResponse;
import com.example.bsm.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private BloodBankRepository bloodBankRepository;



    private Sample mapToSample(SampleRequest sampleRequest, Sample sample) {



        sample.setAvailability(sampleRequest.isAvailability());
        sample.setQuantity(sampleRequest.getQuantity());
        sample.setBloodGroup(sampleRequest.getBloodGroup());
        return sample;
    }

    private SampleResponse getSampleResponse(Sample sample) {

        return SampleResponse.builder()
                .sampleId(sample.getSampleId())
                .availability(sample.isAvailability())
                .availableUnit(sample.getAvailableUnits())
                .bloodGroup(sample.getBloodGroup())
                .emergencyUnit(sample.getEmergencyUnits())
                .quantity(sample.getQuantity())
                .build();
    }
    @Override
    public SampleResponse findSampleById(int sampleId) {
        Sample sample = sampleRepository.findById(sampleId)
            .orElseThrow(() -> new SampleNotFoundByIdException("Fail to find Sample"));
        return this.getSampleResponse(sample);
    }

    @Override
    public SampleResponse updateSample(int sampleId, SampleRequest sampleRequest) {
        Optional<Sample> optional = sampleRepository.findById(sampleId);

        if (optional.isEmpty())
            throw new SampleNotFoundByIdException("Fail to update sample with ID: " );

        Sample sample = this.mapToSample(sampleRequest,optional.get());
        Sample updatedsample = sampleRepository.save(sample);
        return this.getSampleResponse(updatedsample);
    }




    @Override
    public SampleResponse addSample(SampleRequest sampleRequest) {
        Sample sample  = this.mapToSample(sampleRequest, new Sample());
        sample=sampleRepository.save(sample);
        SampleResponse newSample = this.getSampleResponse(sample);
        return newSample;

    }


    @Override
    public SampleResponse registerSample(SampleRequest sampleRequest, int bankId) {
        Optional<BloodBank> optional=bloodBankRepository.findById(bankId);
        if (optional.isEmpty()){
            throw new UserNotFoundByIdException("failed to find Admin");
        }
        BloodBank bloodBank=optional.get();

        Sample sample  = this.mapToSample(sampleRequest, new Sample());

if (bloodBank.getEnergencyUnitCount()<= sampleRequest.getQuantity()) {
    sample.setEmergencyUnits(bloodBank.getEnergencyUnitCount());
    sample.setAvailableUnits(bloodBank.getEnergencyUnitCount()-sample.getQuantity());
}
else {
    sample.setEmergencyUnits(sampleRequest.getQuantity());
    sample.setAvailableUnits(bloodBank.getEnergencyUnitCount()-sample.getQuantity());
}
      sample.setBloodBank(bloodBank);
      Sample sample1=sampleRepository.save(sample);

        return this.getSampleResponse(sample1);
    }


}
