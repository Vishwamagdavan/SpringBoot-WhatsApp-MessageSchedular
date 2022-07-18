package com.project.spring.messagescheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int messageId;
    private String messageContent;
    private int userId;
    private String phoneNumber;
    private Timestamp createdTime;
    private Timestamp scheduledTime;
    private int status;
    private String gupshupMessageId;
}
