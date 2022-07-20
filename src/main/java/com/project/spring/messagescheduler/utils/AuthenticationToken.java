package com.project.spring.messagescheduler.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.UUID;

@Component
public class AuthenticationToken {
    public String generateAuthenticationToken(){
        return UUID.randomUUID().toString();
    }
}
