package com.project.spring.messagescheduler.service;

import com.project.spring.messagescheduler.dto.MessageRequest;
import com.project.spring.messagescheduler.entity.User;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;
import com.project.spring.messagescheduler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AuthRequest {
    @Autowired
    private UserRepository userRepository;

    /**
     * Checks if the user is valid for the message request sent from the client
     * @param authToken auth token from header
     * @param userId user ID and auth token must be same
     * @return true if the user is present or not
     * @throws ResourceNotFoundException display error for the user
     */
    public boolean isValidUser(String authToken,Long userId) throws ResourceNotFoundException {
        if(authToken.isEmpty() || userId == null){
            throw new ResourceNotFoundException("Something went wrong");
        }
        User user=null;
        try {
            user=userRepository.isValidUser(authToken,userId);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return user!=null;
    }
    /**
     * Checks for the valid auth_token header
     * @param authToken auth token from header
     * @return true if the user is present or not
     * @throws ResourceNotFoundException display error for the user
     */
    public boolean isValidToken(String authToken) throws ResourceNotFoundException {
        if(authToken.isEmpty()){
            throw new ResourceNotFoundException("Something went wrong");
        }
        boolean isUserPresent=false;
        try {
            isUserPresent=userRepository.isValidToken(authToken)!=null;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return isUserPresent;
    }
}
