package com.project.spring.messagescheduler.controller;

import com.project.spring.messagescheduler.dto.UserRequest;
import com.project.spring.messagescheduler.entity.User;
import com.project.spring.messagescheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("/adduser")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest){
        return new ResponseEntity<User>(service.addUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody UserRequest userRequest){
        return null;
    }
}
