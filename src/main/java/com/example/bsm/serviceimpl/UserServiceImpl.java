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

    @Override
    public UserResponse addUser(UserRequest userRequest) {
//        return userRepository.save(user);

        User user =User.builder()
                .age(userRequest.getAge())
                .availableCity(userRequest.getAvailableCity())
                .bloodGroup(userRequest.getBloodGroup())
                .gender(userRequest.getGender())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phoneNumber(userRequest.getPhoneNumber())
                .username(userRequest.getUsername())
                .build();
user=userRepository.save(user);
        UserResponse user1 = UserResponse.builder()
                .age(user.getAge())
                .availableCity(user.getAvailableCity())
                .bloodGroup(user.getBloodGroup())
                .gender(user.getGender())
                .lastDenotedDate(user.getLastDenotedDate())
                .verified(user.isVerified())
                .userId(user.getUserId())
                .username(userRequest.getUsername())
                        .build();
return user1;


    }

    @Override
    public UserResponse findUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("Fail to find user"));

        return  UserResponse.builder()
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
    public UserResponse updateUser(int userId, UserRequest user) {
        Optional<User> optional = userRepository.findById(userId);

        if (optional.isPresent()) {
            User foundUser = optional.get();

            // Update fields of the found user
            foundUser.setAge(user.getAge());
            foundUser.setEmail(user.getEmail());
            foundUser.setPassword(user.getPassword());
            foundUser.setUsername(user.getUsername());
            foundUser.setAvailableCity(user.getAvailableCity());
            foundUser.setPhoneNumber(user.getPhoneNumber());

            User updatedUser = userRepository.save(foundUser);


            return UserResponse.builder()
                    .age(updatedUser.getAge())
                    .username(updatedUser.getUsername())
                    .availableCity(updatedUser.getAvailableCity())
                    .verified(updatedUser.isVerified())
                    .lastDenotedDate(updatedUser.getLastDenotedDate())
                    .userId(updatedUser.getUserId())
                    .build();
        } else {
            throw new UserNotFoundByIdException("Fail to update user with ID: " + userId);
        }
    }
}

