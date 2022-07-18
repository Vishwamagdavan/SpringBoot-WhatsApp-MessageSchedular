package com.project.spring.messagescheduler.repository;

import com.project.spring.messagescheduler.entity.Message;
import com.project.spring.messagescheduler.entity.User;

import java.util.List;

public interface MessageRepository {
    int sendMessage(User user);
    Message retrieveMessage(Long messageId);
    List<Message> retrieveAllMessages();
}
