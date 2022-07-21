package com.project.spring.messagescheduler.repository.implmentation;

import com.project.spring.messagescheduler.entity.User;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;
import com.project.spring.messagescheduler.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void isValidUser() throws ResourceNotFoundException{
        try {
            User user=userRepository.isValidUser("c61261e0-542a-4b48-bbec-06662766ea18",7L);
            assertNotNull(user);
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }

    @Test
    void isValidUser1() throws ResourceNotFoundException {
        try {
            User user1=userRepository.isValidUser("82793852-48d6-4b47-8d02-226850dfa0cd",12L);
            assertNotNull(user1);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
    @Test
    void isValidUser2() throws ResourceNotFoundException {
        try {
            User user2=userRepository.isValidUser("c61261e0-542a-4b48-bbec-06662766ea18",79L);
            assertNull(user2);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }


    @Test
    void isValidToken() throws ResourceNotFoundException {
        try {
            assertNotNull(userRepository.isValidToken("82793852-48d6-4b47-8d02-226850dfa0cd"));
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }
}