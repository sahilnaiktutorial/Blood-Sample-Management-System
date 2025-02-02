package com.example.bsm.serviceimpl;

import com.example.bsm.entity.*;
import com.example.bsm.enums.TransactionType;
import com.example.bsm.exception.HospitalNotFoundByIdException;
import com.example.bsm.exception.InsufficientUnitException;
import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.repository.BloodBankRepository;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.repository.TransactionRepository;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.request.TransactionRequest;
import com.example.bsm.response.TransactionResponse;
import com.example.bsm.security.AuthUtil;
import com.example.bsm.service.TransactionService;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private AuthUtil authUtil;

    private TransactionRepository transactionRepository;

    private HospitalRepository hospitalRepository;

    private UserRepository userRepository;

    private BloodBankRepository bloodBankRepository;

    private  Transaction mapToTransaction(TransactionRequest transactionRequest, Transaction transaction) {
        transaction.setDate(transactionRequest.getDate());
        transaction.setTransactionType(transactionRequest.getTransactionType());
        transaction.setTime(transactionRequest.getTime());
        transaction.setNoOfUnits(transactionRequest.getNoOfUnits());
        return transaction;
    }

    private TransactionResponse mapToTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .transactionId(transaction.getTransactionId())
                .transactionType(transaction.getTransactionType())
                .date(transaction.getDate())
                .noOfUnits(transaction.getNoOfUnits())
                .time(transaction.getTime())
                .build();
    }


    @Override
    public TransactionResponse checkTransaction(TransactionRequest transactionRequest,int hospitalId, int userId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(()-> new HospitalNotFoundByIdException("Transaction failed"));
        User user = userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundByIdException("Transaction failed"));
        Admin admin = authUtil.getCurrentAdmin();
        BloodBank bloodBank = admin.getBloodBank();
        Transaction transaction = this.mapToTransaction(transactionRequest, new Transaction());
        List<Sample> samples=bloodBank.getSamples();
        Sample sample=null;
        for (Sample sample1:samples){
            if(sample1.getBloodGroup()==transaction.getBloodGroup()){
                sample=sample1;
            }
        }
        if (sample==null){
            throw new InsufficientUnitException("Not Available");
        }

        int i=sample.getAvailableUnits(),j= sample.getAvailableUnits();
        if(transaction.getTransactionType()== TransactionType.NORMAL){
            if(transaction.getNoOfUnits()>i){
                throw new InsufficientUnitException("Not Available");
            }else {
                sample.setAvailableUnits(i-transaction.getNoOfUnits());

            }
        }else {
            if(transaction.getNoOfUnits()>i){
                if (transaction.getNoOfUnits()>i+sample.getEmergencyUnits()){
                    throw new InsufficientUnitException("Not Found");
                }
                else {
                    sample.setAvailableUnits(0);
                    sample.setEmergencyUnits(sample.getEmergencyUnits()-transaction.getNoOfUnits()+j);

                }
            }else {
                sample.setAvailableUnits(i-transaction.getNoOfUnits());

            }
            sample.setQuantity(sample.getEmergencyUnits()+sample.getAvailableUnits());

            transaction.setUser(user);
            transaction.setHospital(hospital);
            transaction.setBloodBank(bloodBank);
            transactionRepository.save(transaction);


        }
        return this.mapToTransactionResponse(transaction);

    }


}








//@Override
//public TransactionResponse registerUser(TransactionRequest transactionRequest,int hospitalId,int userId) throws Exception {
//    Optional<Hospital> optional=hospitalRepository.findById(hospitalId);
//    if (optional.isEmpty()){
//        throw new HospitalNotFoundByIdException("Failed To find Hospital");
//    }
//    Optional<User> optional1=userRepository.findById(userId);
//    if (optional.isEmpty()){
//        throw new UserNotFoundByIdException("Failed to find user");
//    }
//    Hospital hospital=optional.get();
//    User user=optional1.get();
//    Admin admin=authUtil.getCurrentAdmin();
//    BloodBank bloodBank=admin.getBloodBank();
//    Transaction transaction=this.mapToTransactionRequest(transactionRequest);
//    List<Sample> samples=bloodBank.getSamples();
//    Sample sample=null;
//    for (Sample sample1:samples){
//        if(sample1.getBloodGroup()==transaction.getBloodGroup()){
//            sample=sample1;
//        }
//    }
//    if (sample==null){
//        throw new TransactionNotFoundByIdException("Failed");
//    }
//    int i=sample.getAvailableUnits();
//    if(transaction.getTransactionType()==TransactionType.NORMAL){
//        if(transaction.getNoOfUnits()>i){
//            throw new TransactionNotFoundByIdException("1 Failed");
//        }else {
//            sample.setAvailableUnits(i-transaction.getNoOfUnits());
//        }
//    }else {
//        if(transaction.getNoOfUnits()>i){
//            if (transaction.getNoOfUnits()>i+sample.getEmergencyUnit()){
//                throw new TransactionNotFoundByIdException("2 Failed");
//            }
//            else {
//                sample.setAvailableUnits(0);
//                sample.setEmergencyUnit(sample.getEmergencyUnit()-transaction.getNoOfUnits()+sample.getAvailableUnits());
//            }
//        }else {
//            sample.setAvailableUnits(i-transaction.getNoOfUnits());
//        }
//
//        transaction.setUser(user);
//        transaction.setHospital(hospital);
//        transaction.setBloodBank(bloodBank);
//        transactionRepository.save(transaction);
//
//
//    }
//    return TransactionResponse.builder()
//            .transactionId(transaction.getTransactionId())
//            .noOfUnits(transaction.getNoOfUnits())
//            .build();
//}
//
//public   Transaction mapToTransactionRequest(TransactionRequest transactionRequest) {
//    return Transaction.builder().noOfUnits(transactionRequest.getNoOfUnits())
//            .transactionType(transactionRequest.getTransactionType())
//            .bloodGroup(transactionRequest.getBloodGroup())
//            .build();
//}
//}