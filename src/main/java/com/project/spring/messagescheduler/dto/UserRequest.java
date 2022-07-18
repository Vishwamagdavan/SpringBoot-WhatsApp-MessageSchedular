package com.project.spring.messagescheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotNull(message="Username cannot be not null")
    private String userName;
    private String authToken;
}
