package com.example.bsm.controller;

import com.example.bsm.request.BloodBankRequest;
import com.example.bsm.request.SampleRequest;
import com.example.bsm.response.BloodBankResponse;
import com.example.bsm.response.SampleResponse;
import com.example.bsm.service.SampleService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SampleController {

    private SampleService sampleService;

    private RestResponseBuilder restResponseBuilder;

    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN')")
    @PostMapping("/samples")
    public ResponseEntity<ResponseStructure<SampleResponse>> addSample(@RequestBody  SampleRequest sampleRequest){
        SampleResponse sampleResponse = sampleService.addSample(sampleRequest);
        return restResponseBuilder.success(HttpStatus.CREATED,"sample created",sampleResponse);
    }

    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN')")
    @GetMapping("/samples/{sampleId}")
    public ResponseEntity<ResponseStructure<SampleResponse>> findSampleById(@PathVariable int sampleId){
        SampleResponse sampleResponse = sampleService.findSampleById(sampleId);
        return restResponseBuilder.success(HttpStatus.FOUND,"sample found",sampleResponse);
    }
    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN')")
    @PutMapping("/samples/{sampleId}")
    public ResponseEntity<ResponseStructure<SampleResponse>> updateSample(@PathVariable("bankId") int sampleId, @RequestBody SampleRequest sampleRequest) {
        SampleResponse sampleResponse = sampleService.updateSample(sampleId, sampleRequest);
        return restResponseBuilder.success(HttpStatus.ACCEPTED, "sample updated", sampleResponse);

    }
    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN')")
    @PostMapping("/samples/{bankId}")
    public ResponseEntity<ResponseStructure<SampleResponse>> registerSample(@RequestBody SampleRequest sampleRequest, @PathVariable int bankId){
        SampleResponse sampleResponse = sampleService.registerSample(sampleRequest,bankId);
        return restResponseBuilder.success(HttpStatus.CREATED,"Sample created",sampleResponse);
    }
}
