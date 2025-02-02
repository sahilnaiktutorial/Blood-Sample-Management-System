package com.example.bsm.controller;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.request.BloodBankRequest;
import com.example.bsm.response.BloodBankPageResponse;
import com.example.bsm.response.BloodBankResponse;
import com.example.bsm.service.BloodBankService;
import com.example.bsm.utility.PageStructure;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class BloodBankController {

    private BloodBankService bloodBankService;

    private RestResponseBuilder restResponseBuilder;



    @GetMapping("/bloodbank/{bankId}")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> findBloodBankById(int bankId){
        BloodBankResponse bloodBankResponse = bloodBankService.findBloodBankById(bankId);
        return restResponseBuilder.success(HttpStatus.FOUND,"BloodBank found",bloodBankResponse);
    }
    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN')")
    @PutMapping("/bloodbank/{bankId}")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> updateBloodBank(@PathVariable("bankId") int bankId,@RequestBody BloodBankRequest bloodBankRequest) {
        BloodBankResponse bloodBankResponse=bloodBankService.updateBloodBank(bloodBankRequest,bankId );
        return restResponseBuilder.success(HttpStatus.ACCEPTED,"BloodBank updated",bloodBankResponse);
    }

    @GetMapping("/bloodbanks")
    public ResponseEntity<PageStructure<List<BloodBankPageResponse>>> findAllBloodBankByCity(@RequestParam List<String> city, @RequestParam List<BloodGroup> bloodGroup, @RequestParam int page, @RequestParam int size){
        Page<BloodBankPageResponse> bankResponse = bloodBankService.findAllBloodBankByCity(city,bloodGroup, page, size);
        return restResponseBuilder.success(HttpStatus.FOUND, "BloodBanks Found", bankResponse.toList()
                , bankResponse.getNumber(),
                bankResponse.getTotalPages(),
                bankResponse.getSize());
    }


    @PreAuthorize("hasAuthority('OWNER_ADMIN')")
    @PostMapping("/bloodbanks/{adminId}")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> registerBloodBank(@RequestBody BloodBankRequest  bloodBankRequest,@PathVariable("adminId") int adminId){
        BloodBankResponse bloodBankResponse = bloodBankService.registerBloodBank(bloodBankRequest,adminId);
        return restResponseBuilder.success(HttpStatus.CREATED,"BloodBank created",bloodBankResponse);
    }

}
