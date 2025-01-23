package com.example.bsm.serviceimpl;

import com.example.bsm.entity.User;
import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.request.UserRequest;
import com.example.bsm.response.UserResponse;
import com.example.bsm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    private  User mapToUser(UserRequest userRequest, User user) {
        user.setAge(userRequest.getAge());
        user.setAvailableCity(userRequest.getAvailableCity());
        user.setBloodGroup(userRequest.getBloodGroup());
        user.setGender(userRequest.getGender());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setUsername(userRequest.getUsername());
        return user;
    }



    private  UserResponse getUserResponse(User user) {
        return UserResponse.builder()
                .age(user.getAge())
                .availableCity(user.getAvailableCity())
                .bloodGroup(user.getBloodGroup())
                .gender(user.getGender())
                .lastDenotedDate(user.getLastDenotedDate())
                .verified(user.isVerified())
                .userId(user.getUserId())
                .username(user.getUsername())
                .build();
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
//        return userRepository.save(user);


        User user = this.mapToUser(userRequest, new User());
    user=userRepository.save(user);
        UserResponse user1 = this.getUserResponse(user);
    return user1;
    }

    @Override
    public UserResponse findUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("Fail to find user"));
        return this.getUserResponse(user);
    }

    @Override
    public UserResponse updateUser(int userId, UserRequest user) {
        Optional<User> optional = userRepository.findById(userId);

        if (optional.isEmpty())
            throw new UserNotFoundByIdException("Fail to update user with ID: " + userId);

        User foundUser = this.mapToUser(user,optional.get());
            User updatedUser = userRepository.save(foundUser);
            return this.getUserResponse(updatedUser);
        }
    }

