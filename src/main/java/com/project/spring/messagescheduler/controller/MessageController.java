package com.project.spring.messagescheduler.controller;

import com.project.spring.messagescheduler.dto.MessageRequest;
import com.project.spring.messagescheduler.entity.Message;
import com.project.spring.messagescheduler.exceptions.RecordNotFoundException;
import com.project.spring.messagescheduler.service.AuthRequest;
import com.project.spring.messagescheduler.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private AuthRequest authRequest;

    @Autowired
    private MessageService service;
    @GetMapping("/test")
    public ResponseEntity<String> getTestConnection(){
        return new ResponseEntity<>("Message Controller is working!", HttpStatus.OK);
    }

    @PostMapping("/text")
    @ResponseBody
    public ResponseEntity<Object> sendMessageToClient(@RequestHeader("auth_token") String authToken,@RequestBody MessageRequest messageRequest) throws RecordNotFoundException {
        if(!authRequest.isValidUser(authToken, (long) messageRequest.getUserId())){
            throw new RecordNotFoundException("Invalid User");
        }
        service.saveMessage(messageRequest);
        return null;
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<Message> retrieveMessageData(@PathVariable String id){
        return null;
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> retrieveAllMessages(){
        return null;
    }

    @GetMapping("/messages/{status}")
    public ResponseEntity<List<Message>> retrieveAllStatusMessages(@PathVariable String status){
        return null;
    }
}
