package com.example.bsm.service;

import com.example.bsm.request.BloodBankRequest;
import com.example.bsm.response.BloodBankPageResponse;
import com.example.bsm.response.BloodBankResponse;
import org.springframework.data.domain.Page;
import com.example.bsm.enums.BloodGroup;
import java.util.List;

public interface BloodBankService {



     BloodBankResponse findBloodBankById(int bankId);

     BloodBankResponse updateBloodBank( BloodBankRequest bloodBankRequest,int bankId);

    BloodBankResponse registerBloodBank(BloodBankRequest bloodBankRequest, int adminId);

//    List<BloodBankResponse> findBloodBankByCity(List<String> city);

    Page<BloodBankPageResponse> findAllBloodBankByCity(List<String> city, List<BloodGroup> bloodGroup, int page, int size);
}
