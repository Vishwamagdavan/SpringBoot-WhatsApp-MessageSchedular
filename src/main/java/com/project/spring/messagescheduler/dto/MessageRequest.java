package com.project.spring.messagescheduler.dto;

import com.project.spring.messagescheduler.annotations.CustomDate;
import com.project.spring.messagescheduler.annotations.PhoneNumber;
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
    @PhoneNumber
    private String phoneNumber;
    @CustomDate
    private Timestamp scheduledTime;
}
