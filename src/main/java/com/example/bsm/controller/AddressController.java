package com.example.bsm.controller;

import com.example.bsm.request.AddressRequest;
import com.example.bsm.response.AddressResponse;
import com.example.bsm.service.AddressService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AddressController {
    private AddressService addressService;
    private RestResponseBuilder restResponseBuilder;

    @PostMapping("/userAddress")
    public ResponseEntity<ResponseStructure<AddressResponse>> addAddressToUser(@RequestBody AddressRequest addressRequest) {
        AddressResponse addressResponse = addressService.addAddressToUser(addressRequest);
        return restResponseBuilder.success(HttpStatus.CREATED, "user Address created", addressResponse);
    }


    @PostMapping("/hospitalAddress/{hospitalId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> addAddressToHospital(@RequestBody AddressRequest addressRequest, @PathVariable() int hospitalId) {
        AddressResponse addressResponse = addressService.addAddressToHospital(addressRequest, hospitalId);
        return restResponseBuilder.success(HttpStatus.CREATED, "Hospital Address created", addressResponse);
    }

    @PostMapping("/bloodBankAddress/{bloodBankId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> addAddressToBloodBank(@RequestBody AddressRequest addressRequest, @PathVariable() int bloodBankId) {
        AddressResponse addressResponse = addressService.addAddressToBloodBank(addressRequest, bloodBankId);
        return restResponseBuilder.success(HttpStatus.CREATED, "BloodBank Address created", addressResponse);
    }

    @GetMapping("/Address/{addressId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> findUserById(@PathVariable() int addressId) {
        AddressResponse addressResponse = addressService.findAddressById(addressId);
        return restResponseBuilder.success(HttpStatus.FOUND, "Address found", addressResponse);
    }

    @PutMapping("/Address/{addressId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@RequestBody AddressRequest addressRequest, @PathVariable() int addressId) {
        AddressResponse addressResponse = addressService.updateAddress(addressRequest,addressId);
        return restResponseBuilder.success(HttpStatus.ACCEPTED, "Address updated", addressResponse);
    }
}
