package com.example.bsm.service;

import com.example.bsm.request.UserRequest;
import com.example.bsm.response.UserResponse;

public interface UserService {
    UserResponse addUser(UserRequest user);


    UserResponse findUserById();

    UserResponse updateUser( UserRequest user);


    UserResponse promoteUser(int userId);

    UserResponse addAdminAsUser(UserRequest userRequest);

    UserResponse verifyStatus(boolean isVerified,int userId);

    UserResponse updateUserDate(UserRequest user);
}
