package com.project.spring.messagescheduler.repository;

import com.project.spring.messagescheduler.entity.Message;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;

import java.util.List;

public interface MessageRepository {
    Message saveMessage(Message message) throws ResourceNotFoundException;
    Message retrieveMessage(Long messageId);
    List<Message> retrieveAllMessagesById(Long userId) throws ResourceNotFoundException;

    List<Message> retrieveMessageByStatus(Long userId,int status) throws ResourceNotFoundException;

    List<Message> retrieveAllMessages() throws ResourceNotFoundException;

    int updateStatus(Message message);
}
