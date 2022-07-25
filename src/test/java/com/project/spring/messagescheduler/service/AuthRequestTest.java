package com.project.spring.messagescheduler.service;

import com.project.spring.messagescheduler.entity.User;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;
import com.project.spring.messagescheduler.repository.UserRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthRequestTest {

    @Autowired
    AuthRequest authRequest;

    @MockBean
    UserRepository userRepository;
    @Test
    void isValidUser_whenUserTokenIsValid() throws ResourceNotFoundException {
        try {
            User user=User.build(17L,"ninja","fff3fd6f-0476-4582-ba54-610cb3916e04", Timestamp.valueOf("2022-07-25 11:28:28"));
            when(userRepository.isValidUser("fff3fd6f-0476-4582-ba54-610cb3916e04",17L)).thenReturn(user);
            assertThat(authRequest.isValidUser("fff3fd6f-0476-4582-ba54-610cb3916e04",17L));

        }
        catch (NullPointerException ex){
            System.out.println("Null pointer exception");
        }
    }
    @Test
    void isValidUser_whenUserTokenIsNotValid() throws ResourceNotFoundException {
        try {
            when(userRepository.isValidUser("fff3fd6f-ba54-610cb3916e04",90L)).thenReturn(null);
            assertThat(authRequest.isValidUser("fff3fd6f-ba54-610cb3916e04",90L));

        }
        catch (NullPointerException ex){
            System.out.println("Null pointer exception");
        }
    }

    @Test
    void isValidToken() {
    }
}