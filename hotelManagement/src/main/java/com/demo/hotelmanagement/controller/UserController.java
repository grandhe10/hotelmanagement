package com.demo.hotelmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hotelmanagement.dto.LoginDto;
import com.demo.hotelmanagement.dto.UserDto;
import com.demo.hotelmanagement.exception.ResourceNotFoundException;
import com.demo.hotelmanagement.service.UserService;

 

 

@RestController
public class UserController {
    @Autowired
    UserService userService;

 

    @PostMapping(value = "/users")
    public String registerUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return "user added successfully";
    }

 

    @PostMapping("/users/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto)  {
        
       boolean  isExists=userService.authenticateUser(loginDto.getUserName(),loginDto.getPassword());
        if (isExists)
            return new ResponseEntity<>("logged in successfully",HttpStatus.OK);
        else {
            throw new ResourceNotFoundException("Please enter valid credentials");
        }
    }
}