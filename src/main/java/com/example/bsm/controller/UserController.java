package com.example.bsm.controller;

import com.example.bsm.entity.User;
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
    public ResponseEntity<ResponseStructure<User>> addUser(@RequestBody User user){
        User newUser = userService.addUser(user);
        return restResponseBuilder.success(HttpStatus.CREATED,"user created",newUser);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseStructure<User>> findUserById(@PathVariable int userId){
        User user = userService.findUserById(userId);
        return restResponseBuilder.success(HttpStatus.FOUND,"user found",user);
    }
    @PutMapping("/users/{userid}")
    public ResponseEntity<ResponseStructure<User>> updateActor(@PathVariable("userid") int userId,@RequestBody User user) {
         User updateUser=userService.updateUser(userId,user);
        return restResponseBuilder.success(HttpStatus.ACCEPTED,"user updated",updateUser);
    }


}
