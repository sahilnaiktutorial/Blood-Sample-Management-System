package com.example.bsm.service;

import com.example.bsm.entity.User;

public interface UserService {
    User addUser(User user);


    User findUserById(int userId);

    User updateUser(int userId, User user);
}
