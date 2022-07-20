package com.project.spring.messagescheduler.service;

import com.project.spring.messagescheduler.dto.MessageRequest;
import com.project.spring.messagescheduler.entity.Message;
import com.project.spring.messagescheduler.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository repository;
    public Message saveMessage(MessageRequest messageRequest){
        Message message=Message.build(0,messageRequest.getMessageContent(),messageRequest.getUserId(),messageRequest.getPhoneNumber(),null,messageRequest.getScheduledTime(),0,null,null);
        return repository.saveMessage(message);
    }

    public Message retrieveMessage(Long messageId){
        return repository.retrieveMessage(messageId);
    }

    public List<Message> retrieveAllMessage(Long userId){
        return repository.retrieveAllMessagesById(userId);
    }

    public List<Message> retrieveByStatus(Long userId,int status){
        return repository.retrieveMessageByStatus(userId,status);
    }
}
