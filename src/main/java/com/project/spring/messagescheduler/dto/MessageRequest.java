package com.project.spring.messagescheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    private String messageContent;
    private int userId;
    private String phoneNumber;
    private Timestamp scheduledTime;
}
