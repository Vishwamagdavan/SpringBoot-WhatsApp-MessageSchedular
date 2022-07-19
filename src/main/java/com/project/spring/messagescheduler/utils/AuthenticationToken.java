package com.project.spring.messagescheduler.utils;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.UUID;

public class AuthenticationToken {
    public String generateAuthenticationToken(){
        return UUID.randomUUID().toString();
    }
}
