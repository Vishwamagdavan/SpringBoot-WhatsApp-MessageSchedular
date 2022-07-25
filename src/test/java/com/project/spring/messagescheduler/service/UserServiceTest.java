package com.project.spring.messagescheduler.service;

import com.project.spring.messagescheduler.dto.UserRequest;
import com.project.spring.messagescheduler.entity.User;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;
import com.project.spring.messagescheduler.repository.MessageRepository;
import com.project.spring.messagescheduler.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @MockBean
    UserRepository userRepository;
    @Autowired
    UserService userService;
    Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    private UserRequest userRequest;

    @BeforeEach
    public void setUpInstance(){
    }

    @Test
    public void whenUserRequestIsNotEmpty() throws ResourceNotFoundException {
        User user=User.build(17L,"ninja","fff3fd6f-0476-4582-ba54-610cb3916e04",Timestamp.valueOf("2022-07-25 11:28:28"));
        when(userRepository.saveUser(user)).thenReturn(Optional.ofNullable(user));
        assertThat(userService.addUser(UserRequest.build("ninja")).isPresent());
    }

    @Test
    public void whenUserRequestEmpty() throws ResourceNotFoundException {
        User user=null;
        when(userRepository.saveUser(null)).thenReturn(null);
        assertThat(userService.addUser(UserRequest.build(null))).isEmpty();
    }
}