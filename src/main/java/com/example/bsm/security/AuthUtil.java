package com.example.bsm.security;

import com.example.bsm.entity.Admin;
import com.example.bsm.entity.User;
import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component// because current class should alo be bean so that we can make con
@AllArgsConstructor
public class AuthUtil {

    private UserRepository userRepository;

    private AdminRepository adminRepository;

    public  static String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getCurrentUser(){

        //lamda expression gives direct object not optional
        return userRepository.findByEmail(this.getCurrentUsername())
                .orElseThrow(()->new UserNotFoundByIdException("User not found"));

    }

    public  String getCurrentAdminName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    public Admin getCurrentAdmin(){
        return adminRepository.findByUser_Email(this.getCurrentAdminName())
                .orElseThrow(()->new UserNotFoundByIdException("Admin not found"));
    }


}
