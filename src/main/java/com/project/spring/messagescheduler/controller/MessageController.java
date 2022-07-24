package com.project.spring.messagescheduler.controller;

import com.project.spring.messagescheduler.dto.MessageRequest;
import com.project.spring.messagescheduler.entity.Message;
import com.project.spring.messagescheduler.exceptions.AuthFailedException;
import com.project.spring.messagescheduler.exceptions.InputExceptions;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;
import com.project.spring.messagescheduler.service.AuthRequest;
import com.project.spring.messagescheduler.service.MessageService;
import com.project.spring.messagescheduler.utils.StatusUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    private Logger logger= LoggerFactory.getLogger(MessageController.class);
    @Autowired
    private AuthRequest authRequest;

    @Autowired
    private StatusUtil statusUtil;

    @Autowired
    private MessageService service;
    @GetMapping("/test")
    public ResponseEntity<String> getTestConnection(){
        return new ResponseEntity<>("Message Controller is working!", HttpStatus.OK);
    }

    @PostMapping("/text")
    @ResponseBody
    public ResponseEntity<Message> sendMessageToClient(@RequestHeader(value ="auth_token", required = true) String authToken,@RequestBody MessageRequest messageRequest) throws AuthFailedException, ResourceNotFoundException {
        logger.info(authToken);
        if(!authRequest.isValidUser(authToken, (long) messageRequest.getUserId())){
            throw new AuthFailedException("Invalid User");
        }
        Optional<Message> message=service.saveMessage(messageRequest);
        if(!message.isPresent()){
            throw new ResourceNotFoundException("Something went wrong");
        }
        return new ResponseEntity<>(message.get(),HttpStatus.CREATED);
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<Message> retrieveMessageData(@RequestHeader("auth_token") String authToken,@PathVariable Long id,@RequestParam("messageId") Long messageId) throws AuthFailedException, ResourceNotFoundException {
        if(!authRequest.isValidUser(authToken,id)){
            throw new AuthFailedException("Invalid User");
        }
        return new ResponseEntity<>(service.retrieveMessage(messageId),HttpStatus.OK);
    }

    @GetMapping("/allmessages/{userId}")
    public ResponseEntity<List<Message>> retrieveAllMessages(@RequestHeader("auth_token") String authToken,@PathVariable Long userId) throws AuthFailedException, ResourceNotFoundException {
        if(!authRequest.isValidUser(authToken,userId)){
            throw new AuthFailedException("Invalid User");
        }
        return new ResponseEntity<>(service.retrieveAllMessage(userId),HttpStatus.OK);
    }

    @GetMapping("/message/{userId}/{status}")
    public ResponseEntity<List<Message>> retrieveAllStatusMessages(@RequestHeader("auth_token") String authToken,@PathVariable Long userId,@PathVariable String status) throws AuthFailedException, InputExceptions, ResourceNotFoundException {
        if(!authRequest.isValidUser(authToken,userId)){
            throw new AuthFailedException("Invalid User");
        }
        int statusCode=statusUtil.getStatus(status);
        if(statusCode==-1)
            throw new InputExceptions("Wrong Status Type");
        return new ResponseEntity<>(service.retrieveByStatus(userId,statusCode),HttpStatus.OK);
    }
}