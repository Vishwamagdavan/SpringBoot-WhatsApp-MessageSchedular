package com.project.spring.messagescheduler.utils;

import java.security.SecureRandom;
import java.util.Arrays;

public class AuthenticationToken {
    private final SecureRandom SECURE_RANDOM=new SecureRandom();

    public String generateAuthenticationToken(){
        byte[] bytes = new byte[20];
        this.SECURE_RANDOM.nextBytes(bytes);
        return Arrays.toString(bytes);
    }
}
