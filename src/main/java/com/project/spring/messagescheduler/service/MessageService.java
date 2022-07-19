package com.project.spring.messagescheduler.service;

import com.project.spring.messagescheduler.dto.MessageRequest;
import com.project.spring.messagescheduler.entity.Message;
import com.project.spring.messagescheduler.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository repository;
    public Message saveMessage(MessageRequest messageRequest){
        Message message=Message.build(0,messageRequest.getMessageContent(),messageRequest.getUserId(),messageRequest.getPhoneNumber(),null,null,0,null,null);
        repository.saveMessage(message);
        return null;
    }
}
