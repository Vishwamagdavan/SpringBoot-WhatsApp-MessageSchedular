package com.project.spring.messagescheduler.repository.implmentation;

import com.project.spring.messagescheduler.entity.Message;
import com.project.spring.messagescheduler.entity.User;
import com.project.spring.messagescheduler.repository.MessageRepository;

import java.util.List;

public class MessageRepositoryImpl implements MessageRepository {
    @Override
    public int sendMessage(User user) {
        return 0;
    }

    @Override
    public Message retrieveMessage(Long messageId) {
        return null;
    }

    @Override
    public List<Message> retrieveAllMessages() {
        return null;
    }

    @Override
    public List<Message> retrieveMessageByStatus(int type) {
        return null;
    }
}
