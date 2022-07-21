package com.project.spring.messagescheduler.service;

import com.project.spring.messagescheduler.dto.MessageRequest;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;
import com.project.spring.messagescheduler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthRequest {
    @Autowired
    private UserRepository userRepository;
    public boolean isValidUser(String authToken,Long userId) throws ResourceNotFoundException {
        return userRepository.isValidUser(authToken,userId)!=null;
    }

    public boolean isValidToken(String authToken) throws ResourceNotFoundException {
        return userRepository.isValidToken(authToken)!=null;
    }
}
