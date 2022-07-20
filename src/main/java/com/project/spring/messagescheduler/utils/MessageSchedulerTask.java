package com.project.spring.messagescheduler.utils;

import com.project.spring.messagescheduler.entity.Message;
import com.project.spring.messagescheduler.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

@Component
public class MessageSchedulerTask extends TimerTask {
    @Autowired
    private MessageRepository messageRepository;
    Logger logger= LoggerFactory.getLogger(MessageSchedulerTask.class);

    List<Message> pollFromDatabase() throws Exception {
        List<Message> messageGroup=messageRepository.retrieveAllMessages();
        if(messageGroup==null)
            throw new Exception("Problem in Fetching file");
        return messageGroup;
    }

    public void scheduleMessage() throws Exception {

    }

    public void webRequestToGupshupAPI(){
        
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);

        List<Message> messages= null;
        try {
            messages = pollFromDatabase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for(Message message: messages){
            System.out.println("Timer is working");
        }
        logger.info("Java cron job expression:: " + strDate);
    }
}
