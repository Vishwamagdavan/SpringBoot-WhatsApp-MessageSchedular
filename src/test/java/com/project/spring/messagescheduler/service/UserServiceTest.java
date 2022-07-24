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
        userRequest=UserRequest.build(5,"Vishva","", Timestamp.from(Instant.now()));
    }

    @Test
    public void whenUserRequestIsEmpty() throws ResourceNotFoundException {
        User user=User.build(14L,"Vishva","",null);
        given(userService.addUser(userRequest)).willReturn(user);
        assertNull(user);

    }
}