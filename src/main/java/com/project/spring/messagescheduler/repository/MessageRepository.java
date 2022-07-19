package com.project.spring.messagescheduler.repository;

import com.project.spring.messagescheduler.entity.Message;

import java.util.List;

public interface MessageRepository {
    Message saveMessage(Message message);
    Message retrieveMessage(Long messageId);
    List<Message> retrieveAllMessages(Long userId);

    List<Message> retrieveMessageByStatus(Long userId,int status);
}
