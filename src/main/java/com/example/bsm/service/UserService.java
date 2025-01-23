package com.example.bsm.service;

import com.example.bsm.entity.User;
import com.example.bsm.request.UserRequest;
import com.example.bsm.response.UserResponse;

public interface UserService {
    UserResponse addUser(UserRequest user);


    UserResponse findUserById(int userId);

    UserResponse updateUser(int userId, UserRequest user);
}
