package com.project.spring.messagescheduler.dto;

import com.project.spring.messagescheduler.annotations.CustomDate;
import com.project.spring.messagescheduler.annotations.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    @NotBlank(message = "Message Should not be empty")
    private String messageContent;
    @NotNull
    private Long userId;
    @NotBlank(message = "Please provide Phone number")
    @PhoneNumber
    private String phoneNumber;
    @NotNull(message = "please provide time for scheduling")
    @CustomDate
    private Timestamp scheduledTime;
}
