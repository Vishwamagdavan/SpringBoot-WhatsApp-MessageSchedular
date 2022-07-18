package com.project.spring.messagescheduler.controller;

import com.project.spring.messagescheduler.dto.MessageRequest;
import com.project.spring.messagescheduler.entity.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @GetMapping("/test")
    public ResponseEntity<String> getTestConnection(){
        return new ResponseEntity<>("Message Controller is working!", HttpStatus.OK);
    }

    @PostMapping("/text")
    @ResponseBody
    public ResponseEntity<Object> sendMessageToClient(@RequestBody MessageRequest messageRequest){
        return null;
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<Message> retrieveMessageData(@PathVariable Long messageId){
        return null;
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> retrieveAllMessages(){
        return null;
    }
}