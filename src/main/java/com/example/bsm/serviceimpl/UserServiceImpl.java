package com.example.bsm.serviceimpl;

import com.example.bsm.entity.User;
import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(int userId) {
        Optional<User> newUser = userRepository.findById(userId);
        if (newUser.isPresent()) {
            User foundUser = newUser.get();
            return foundUser;
        } else {
            throw new UserNotFoundByIdException("Fail to find new user");

        }
    }

    @Override
    public User updateUser(int userId, User user) {
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isPresent()) {
            User foundUser = optional.get();

            foundUser.setAge(user.getAge());
            foundUser.setEmail(user.getEmail());
            foundUser.setPassword(user.getPassword());
            foundUser.setUsername(user.getUsername());
            foundUser.setAvailableCity(user.getAvailableCity());
            foundUser.setVerified(user.isVerified());
            foundUser.setPhoneNumber(user.getPhoneNumber());
            foundUser.setLastDenotedDate(user.getLastDenotedDate());

            return userRepository.save(foundUser);
        } else {
            throw new UserNotFoundByIdException("Fail to update user");

        }
    }
}

