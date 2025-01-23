package com.example.bsm.controller;

import com.example.bsm.entity.User;
import com.example.bsm.request.UserRequest;
import com.example.bsm.response.UserResponse;
import com.example.bsm.service.UserService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {


private UserService userService;

private RestResponseBuilder restResponseBuilder;



    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody UserRequest userRequest){
        UserResponse user = userService.addUser(userRequest);
        return restResponseBuilder.success(HttpStatus.CREATED,"user created",user);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable int userId){
        UserResponse user = userService.findUserById(userId);
        return restResponseBuilder.success(HttpStatus.FOUND,"user found",user);
    }
    @PutMapping("/users/{userid}")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@PathVariable("userid") int userId,@RequestBody UserRequest user) {
        UserResponse updateUser=userService.updateUser(userId,user);
        return restResponseBuilder.success(HttpStatus.ACCEPTED,"user updated",updateUser);
    }


}
