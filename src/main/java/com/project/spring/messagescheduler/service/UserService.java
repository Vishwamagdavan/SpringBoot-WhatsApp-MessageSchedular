package com.project.spring.messagescheduler.service;

import com.project.spring.messagescheduler.dto.UserRequest;
import com.project.spring.messagescheduler.entity.User;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;
import com.project.spring.messagescheduler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User addUser(UserRequest userRequest) throws ResourceNotFoundException {
        User user=User.build(0,userRequest.getUserName(),null,null);
        return userRepository.saveUser(user);
    }
}
