package com.project.spring.messagescheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    @NotNull(message = "Message Should not be empty")
    private String messageContent;
    @NotNull(message = "Please provide user name")
    private int userId;
    @NotNull(message = "Please provide Phone number")
    @Pattern(regexp = "^\\+[1-9]{1}[0-9]{3,14}$",message = "Enter valid phone number")
    private String phoneNumber;
    private Timestamp scheduledTime;
}
