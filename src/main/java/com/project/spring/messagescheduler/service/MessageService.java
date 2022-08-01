package com.project.spring.messagescheduler.service;

import com.project.spring.messagescheduler.dto.MessageRequest;
import com.project.spring.messagescheduler.entity.Message;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;
import com.project.spring.messagescheduler.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final Logger logger= LoggerFactory.getLogger(MessageService.class);
    @Autowired
    private MessageRepository repository;
    /**
     * Message request is converted into message object and then the saving done using Prepared statement with keyHolder, the keyholder gather the primary key and returns to the user
     * @param messageRequest contains message,time to send other details
     * @return Message object
     * @throws ResourceNotFoundException Throws exceptions to the user
     */
    public Optional<Message> saveMessage(MessageRequest messageRequest) throws ResourceNotFoundException {
        Message message=Message.build(0,messageRequest.getMessageContent(),messageRequest.getUserId(),
                messageRequest.getPhoneNumber(),null, Timestamp.valueOf(messageRequest.getScheduledTime()),0,null,null);

        Message result=repository.saveMessage(message);
        if(result==null)
            return Optional.empty();
        return Optional.of(result);
    }
    /**
     * The message is retrieved using message ID
     * @param messageId the user sends the messageID
     * @return returns message object or else null
     */
    public Message retrieveMessage(Long messageId) throws ResourceNotFoundException {
        if(messageId==null)
            throw new ResourceNotFoundException("Something went wrong");
        Message message=null;
        try {
            message=repository.retrieveMessage(messageId);
        }catch (Exception exception){

            logger.info(exception.toString());
            throw new ResourceNotFoundException("Something went wrong");
        }
        finally {
            logger.info("Message ");
            return message;
        }
    }
    /**
     * Method returns all the message by the user
     * @param userId userID must be valid userId
     * @return List of Message object or null
     * @throws ResourceNotFoundException throws exception and displayed to the user
     */
    public List<Message> retrieveAllMessage(Long userId) throws ResourceNotFoundException {
        if(userId==null)
            throw new ResourceNotFoundException("Something went wrong");
        List<Message> messages=null;
        try {
            messages=repository.retrieveAllMessagesById(userId);
        }
        catch (Exception exception){

            logger.info(exception.toString());
            throw new ResourceNotFoundException("Something went wrong");
        }
        finally {
            return messages;
        }
    }
    /**
     * The method is used to display list of the message by the status
     * @param userId user must be valid user, else it throws an error
     * @param status status must be between 0-2
     * @return List of message or null
     * @throws ResourceNotFoundException throws exception to display in the user request object
     */
    public List<Message> retrieveByStatus(Long userId,int status) throws ResourceNotFoundException {
        if(userId==null)
            throw new ResourceNotFoundException("Something went wrong");
        List<Message> messages=null;
        try {
            messages=repository.retrieveMessageByStatus(userId,status);
        }
        catch (Exception exception){
            logger.info(exception.toString());
            throw new ResourceNotFoundException("Something went wrong");
        }
        finally {
            return messages;
        }
    }

}
