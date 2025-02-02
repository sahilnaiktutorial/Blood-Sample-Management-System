package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Address;
import com.example.bsm.entity.BloodBank;
import com.example.bsm.entity.Hospital;
import com.example.bsm.entity.User;
import com.example.bsm.enums.Role;
import com.example.bsm.exception.BloodBankNotFoundByIdException;
import com.example.bsm.exception.HospitalNotFoundByIdException;
import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.repository.AddressRepository;
import com.example.bsm.repository.BloodBankRepository;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.request.AddressRequest;
import com.example.bsm.request.UserRequest;
import com.example.bsm.response.AddressResponse;
import com.example.bsm.response.UserResponse;
import com.example.bsm.security.AuthUtil;
import com.example.bsm.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class AddressServiceImpl implements AddressService {



    private AuthUtil authUtil;

    private UserRepository userRepository;

    private AddressRepository addressRepository;

    private HospitalRepository hospitalRepository;

    private BloodBankRepository bloodBankRepository;

    private Address mapToAddress(AddressRequest addressRequest, Address address) {
     address.setAddressLine(addressRequest.getAddressLine());
     address.setState(addressRequest.getState());
     address.setCity(addressRequest.getCity());
     address.setArea(addressRequest.getArea());
     address.setLandMark(addressRequest.getLandMark());
     address.setCountry(addressRequest.getCountry());
     address.setPincode(addressRequest.getPincode());
        return address;
    }



    private AddressResponse getAdddressResponse(Address address) {
        return AddressResponse.builder()
                .addressId(address.getAddressId())
                .area(address.getArea())
                .state(address.getState())
                .city(address.getCity())
                .addressLine(address.getAddressLine())
                .country(address.getCountry())
                .pincode(address.getPincode())
                .landMark(address.getLandMark())
                .build();

    }


    @Override
    public AddressResponse addAddressToUser(AddressRequest addressRequest) {
        User user = authUtil.getCurrentUser();
        Address address = this.mapToAddress(addressRequest,new Address());
        user.setAddress(address);
        addressRepository.save(address);
        userRepository.save(user);

        return getAdddressResponse(address);
    }

    @Override
    public AddressResponse addAddressToHospital(AddressRequest addressRequest, int hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundByIdException("Fail to find Hospital"));
        Address address = this.mapToAddress(addressRequest,new Address());
        hospital.setAddress(address);
        addressRepository.save(address);
        hospitalRepository.save(hospital);
        return getAdddressResponse(address);
    }

    @Override
    public AddressResponse addAddressToBloodBank(AddressRequest addressRequest, int bankId) {
        BloodBank bloodBank = bloodBankRepository.findById(bankId)
                .orElseThrow(() -> new BloodBankNotFoundByIdException("Fail to find BloodBank"));
        Address address = this.mapToAddress(addressRequest,new Address());
        bloodBank.setAddress(address);
        addressRepository.save(address);
        bloodBankRepository.save(bloodBank);
        return getAdddressResponse(address);

    }

    @Override
    public AddressResponse findAddressById(int addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new UserNotFoundByIdException("Fail to find user"));
        return this.getAdddressResponse(address);
    }

    @Override
    public AddressResponse updateAddress(AddressRequest addressRequest,int addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new UserNotFoundByIdException("Fail to find user"));
        Address newAddress =this.mapToAddress(addressRequest,address);
        addressRepository.save(newAddress);
        return this.getAdddressResponse(address);


    }


}
