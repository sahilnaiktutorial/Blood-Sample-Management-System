package com.example.bsm.service;

import com.example.bsm.request.HospitalRequest;
import com.example.bsm.response.HospitalResponse;
import jakarta.validation.Valid;

public interface HospitalService {



    HospitalResponse findHospitalById(int hospitalId);

    HospitalResponse updateHospital(int hospitalId, HospitalRequest hospitalRequest);

    HospitalResponse registerHospital(HospitalRequest hospitalRequest, int adminId);
}
