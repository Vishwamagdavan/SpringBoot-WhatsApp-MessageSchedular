package com.project.spring.messagescheduler.repository.implmentation;

import com.project.spring.messagescheduler.entity.Message;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MessageRepositoryImplTest {

    @Autowired
    private MessageRepositoryImpl messageRepository;

    private final Logger logger= LoggerFactory.getLogger(MessageRepositoryImplTest.class);

    @Test
    void saveMessage() throws ResourceNotFoundException {
        Message message=Message.build(0,"Testing for Message Repository",8,"919952862652",Timestamp.from(Instant.now()),Timestamp.from(Instant.now()),0,null,null);
        Message result=messageRepository.saveMessage(message);
        assertNotNull(result);
        assertEquals(result.getStatus(),0);
    }

    @Test
    void saveMesssageWhenNull() throws ResourceNotFoundException {
        try {
            Message result=messageRepository.saveMessage(null);
            assertNull(result);

        }
        catch (NullPointerException ex){
        }
    }

    @Test
    void retrieveMessage() {
        Message result=messageRepository.retrieveMessage(16L);
        assertNotNull(result);
    }

    @Test
    void retrieveAllMessagesById() throws ResourceNotFoundException {
        assertNotNull(messageRepository.retrieveAllMessagesById(12L));
    }

    @Test
    void retrieveMessageByStatus() throws ResourceNotFoundException {
        assertNotNull(messageRepository.retrieveMessageByStatus(12L,0));
    }

    @Test
    void retrieveAllMessages() throws ResourceNotFoundException {
        assertNotNull(messageRepository.retrieveAllMessages());
    }

    @Test
    void updateStatus() {
        Message message=messageRepository.retrieveMessage(16L);
        assertEquals(messageRepository.updateStatus(message),1);
    }
}