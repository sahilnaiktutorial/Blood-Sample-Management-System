package com.example.bsm.controller;

import com.example.bsm.entity.User;
import com.example.bsm.request.UserRequest;
import com.example.bsm.response.UserResponse;
import com.example.bsm.service.UserService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {


    private UserService userService;
    private RestResponseBuilder restResponseBuilder;


    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody @Valid UserRequest userRequest) {
        UserResponse user = userService.addUser(userRequest);
        return restResponseBuilder.success(HttpStatus.CREATED, "user created", user);
    }

    @GetMapping("/Accounts")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById() {
        UserResponse user = userService.findUserById();
        return restResponseBuilder.success(HttpStatus.FOUND, "user found", user);
    }

    @PutMapping("/users")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestBody UserRequest user) {
        UserResponse updateUser = userService.updateUser(user);
        return restResponseBuilder.success(HttpStatus.ACCEPTED, "user updated", updateUser);
    }

    @PreAuthorize("hasAuthority('OWNER_ADMIN')")
    @PutMapping("user/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> promoteUser(@PathVariable() int userId) {
        UserResponse user = userService.promoteUser(userId);
        return restResponseBuilder.success(HttpStatus.CREATED, "user created", user);
    }

    @PostMapping("/Admin")
    public ResponseEntity<ResponseStructure<UserResponse>> addAdminAsUser(@RequestBody UserRequest userRequest) {
        UserResponse user = userService.addAdminAsUser(userRequest);
        return restResponseBuilder.success(HttpStatus.CREATED, "user created", user);
    }

    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN')")
    @PatchMapping("/users/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> verifyStatus(@RequestParam boolean isVerified, @PathVariable int userId) {
        UserResponse user = userService.verifyStatus(isVerified, userId);
        return restResponseBuilder.success(HttpStatus.CREATED, "user created", user);
    }

    @PatchMapping("/lastDates")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUserDate(@RequestBody UserRequest user) {
        UserResponse updateUser = userService.updateUserDate(user);
        return restResponseBuilder.success(HttpStatus.ACCEPTED, "user updated", updateUser);
    }
}
