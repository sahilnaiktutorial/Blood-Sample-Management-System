package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Admin;
import com.example.bsm.entity.User;
import com.example.bsm.enums.Role;
import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.request.UserRequest;
import com.example.bsm.response.UserResponse;
import com.example.bsm.security.AuthUtil;
import com.example.bsm.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private AdminRepository adminRepository;

    private PasswordEncoder passwordEncoder;

    private AuthUtil authUtil;


    private  User mapToUser(UserRequest userRequest, User user) {
        user.setAge(userRequest.getAge());
        user.setAvailableCity(userRequest.getAvailableCity());
        user.setBloodGroup(userRequest.getBloodGroup());
        user.setGender(userRequest.getGender());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setUsername(userRequest.getUsername());
        user.setLastDenotedDate(userRequest.getLastDenotedDate());

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
                .role(user.getRole())
                .build();
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = this.mapToUser(userRequest, new User());
        user.setRole(Role.valueOf("USER"));
        //encodeing user password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    user=userRepository.save(user);
        UserResponse user1 = this.getUserResponse(user);
    return user1;
    }

    @Override
    public UserResponse findUserById() {
        //finding by auth user
        int userId = authUtil.getCurrentUser().getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("Fail to find user"));
        return this.getUserResponse(user);
    }

    @Override
    public UserResponse updateUser(UserRequest user) {
        User exuser = authUtil.getCurrentUser();
        User foundUser = this.mapToUser(user,exuser);
//        foundUser.setPassword(passwordEncoder.encode(foundUser.getPassword()));
            User updatedUser = userRepository.save(foundUser);
            return this.getUserResponse(updatedUser);
        }


    @Override
    public UserResponse promoteUser(int userId) {
        Optional<User> optional = userRepository.findById(userId);

        if (optional.isEmpty())
            throw new UserNotFoundByIdException("Fail to update user with ID: " + userId);
        User exuser = optional.get();


       User ownerUser=authUtil.getCurrentUser();
        if (ownerUser.getRole()!=Role.OWNER_ADMIN)
            throw new UserNotFoundByIdException("Fail to update user with ID: " + userId);


        exuser.setRole(Role.GUEST_ADMIN);
        userRepository.save(exuser);
        Admin admin = Admin.builder()
                .user(exuser)
                .build();
        adminRepository.save(admin);
        return this.getUserResponse(exuser);
    }

    @Override
    public UserResponse addAdminAsUser(UserRequest userRequest) {
        User user = this.mapToUser(userRequest, new User());
        user.setRole(Role.OWNER_ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user=userRepository.save(user);
        Admin admin = Admin.builder()
                .user(user)
                .build();
        adminRepository.save(admin);

        return this.getUserResponse(user);
    }

    @Override
    public UserResponse verifyStatus(boolean isVerified, int userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("Fail to find user"));

            user.setVerified(isVerified);
        user=userRepository.save(user);
        return this.getUserResponse(user);
    }

    @Override
    public UserResponse updateUserDate(UserRequest userRequest) {
        User exuser = authUtil.getCurrentUser();
        User foundUser = this.mapToUser(userRequest,exuser);
        User updatedUser = userRepository.save(foundUser);
        return this.getUserResponse(updatedUser);
    }

    }




