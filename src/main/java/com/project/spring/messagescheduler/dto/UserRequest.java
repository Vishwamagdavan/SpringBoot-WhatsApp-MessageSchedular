package com.project.spring.messagescheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName ="build")
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message="Username cannot be not null")
    private String userName;
}
