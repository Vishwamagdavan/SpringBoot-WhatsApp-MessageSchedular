package com.project.spring.messagescheduler.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class User {
    private long userId;
    private String userName;
    private String authToken;
    private Timestamp createdAt;
}
