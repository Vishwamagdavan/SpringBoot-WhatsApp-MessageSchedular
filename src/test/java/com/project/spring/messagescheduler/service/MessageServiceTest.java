package com.project.spring.messagescheduler.service;

import com.project.spring.messagescheduler.dto.MessageRequest;
import com.project.spring.messagescheduler.entity.Message;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;
import com.project.spring.messagescheduler.repository.MessageRepository;
import com.project.spring.messagescheduler.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {
    private Logger logger= LoggerFactory.getLogger(MessageServiceTest.class);
    @Autowired
    private MessageService service;

    @MockBean
    private MessageRepository repository;

    @Test
    public void saveMessage_whenMessageRequestIsValid() throws ResourceNotFoundException {
        Timestamp currentTime=Timestamp.from(Instant.now());
        Message message=Message.build(10L,"Hi from gupshup",17L,
                "919952862652", currentTime,currentTime,0,
                "124a365a-0cd6-11ed-861d-0242ac120002",currentTime);
        when(repository.saveMessage(message)).thenReturn(message);
        MessageRequest messageRequest=new MessageRequest("Hi from gupshup",17L,
                "919952862652",currentTime.toString());
        assertEquals(service.saveMessage(messageRequest),Optional.of(message));
    }
}