package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Admin;
import com.example.bsm.entity.Hospital;
import com.example.bsm.exception.HospitalNotFoundByIdException;
import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.request.HospitalRequest;
import com.example.bsm.response.HospitalResponse;
import com.example.bsm.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;


    private  Hospital mapToHospital(HospitalRequest hospitalRequest, Hospital hospital) {
        hospital.setName(hospitalRequest.getName());
        return hospital;
    }

    private  HospitalResponse getHospitalResponse(Hospital hospital) {
        return HospitalResponse.builder()
                .hospitalId(hospital.getHospitalId())
                .name(hospital.getName())
                .build();
    }

    @Override
    public HospitalResponse findHospitalById(int hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundByIdException("Fail to find Hospital"));
        return this.getHospitalResponse(hospital);
    }


    @Override
    public HospitalResponse updateHospital(int hospitalId, HospitalRequest hospitalRequest) {
        Optional<Hospital> optional = hospitalRepository.findById(hospitalId);

        if (optional.isEmpty())
            throw new HospitalNotFoundByIdException("Fail to update Hospital with ID: " + hospitalId);

        Hospital hospital = this.mapToHospital(hospitalRequest,optional.get());
        Hospital updatedHospital = hospitalRepository.save(hospital);
        return this.getHospitalResponse(updatedHospital);
    }



    private  Hospital mapToHospitalReq(HospitalRequest hospitalRequest, Hospital hospital) {
        hospital.setName(hospitalRequest.getName());

        return hospital;
    }

    private HospitalResponse mapToHospitalRes(Hospital hospital) {
        return HospitalResponse.builder()
                .hospitalId(hospital.getHospitalId())
                .name(hospital.getName())
                .build();
    }

    @Override
    public HospitalResponse registerHospital(HospitalRequest hospitalRequest, int adminId) {
            Optional<Admin> optional=adminRepository.findById(adminId);
            if (optional.isEmpty()){
                throw new UserNotFoundByIdException("failed to find Admin");
            }
            Admin admin=optional.get();
            List<Admin> adminList=new ArrayList<>();
        adminList.add(admin);
            Hospital hospital=Hospital.builder()
                    .adminList(adminList)
                    .name(hospitalRequest.getName())
                    .build();
        adminRepository.save(admin);

            hospital=hospitalRepository.save(hospital);

            return this.mapToHospitalRes(hospital);

    }


}
