package com.project.spring.messagescheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Message {
    private long messageId;
    private String messageContent;
    private long userId;
    private String phoneNumber;
    private Timestamp createdAt;
    private Timestamp scheduledAt;
    private int status;
    private String gupshupMessageId;
    private Timestamp sentAt;
}
