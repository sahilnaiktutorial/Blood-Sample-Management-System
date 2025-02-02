package com.example.bsm.service;

import com.example.bsm.request.AddressRequest;
import com.example.bsm.response.AddressResponse;

public interface AddressService {

    AddressResponse addAddressToUser(AddressRequest addressRequest);

    AddressResponse addAddressToHospital(AddressRequest addressRequest, int hospitalId);

    AddressResponse addAddressToBloodBank(AddressRequest addressRequest, int bloodBankId);

    AddressResponse findAddressById(int addressId);

    AddressResponse updateAddress(AddressRequest addressRequest,int addressId);
}
