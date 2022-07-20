package com.project.spring.messagescheduler.repository;

import com.project.spring.messagescheduler.entity.Message;

import java.util.List;

public interface MessageRepository {
    Message saveMessage(Message message);
    Message retrieveMessage(Long messageId);
    List<Message> retrieveAllMessagesById(Long userId);

    List<Message> retrieveMessageByStatus(Long userId,int status);

    List<Message> retrieveAllMessages();

    int updateStatus(Message message);
}
