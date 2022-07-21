package com.project.spring.messagescheduler.utils;

import com.project.spring.messagescheduler.entity.Message;
import com.project.spring.messagescheduler.repository.MessageRepository;
import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Component
public class MessageSchedulerTask extends TimerTask {

    private final MessageHttpClient httpCall;
    private final ApplicationParser applicationParser;
    private final MessageRepository messageRepository;
    private final Logger logger= LoggerFactory.getLogger(MessageSchedulerTask.class);

    public MessageSchedulerTask(MessageHttpClient httpCall, ApplicationParser applicationParser, MessageRepository messageRepository) {
        this.httpCall = httpCall;
        this.applicationParser = applicationParser;
        this.messageRepository = messageRepository;
    }

    List<Message> pollFromDatabase() throws Exception {
        List<Message> messageGroup=messageRepository.retrieveAllMessages();
        if(messageGroup==null)
            throw new Exception("Problem in Fetching file");
        return messageGroup;
    }

    @Override
    public void run() {
        List<Message> messages= null;
        try {
            messages = pollFromDatabase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for(Message message: messages){
            ResponseBody responseBody=httpCall.httpClientPostRequest(message);
            HashMap<String,Object> result= null;
            try {
                result = applicationParser.convertStringToObject(responseBody.string());
                message.setGupshupMessageId(String.valueOf(result.get("messageId")));
                message.setStatus(2);
                message.setSentAt(Timestamp.from(Instant.now()));
                messageRepository.updateStatus(message);
                logger.info(message.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            catch (NullPointerException nullPointerException){
                logger.info("Failed to Connect API");
            }

        }
    }
}
