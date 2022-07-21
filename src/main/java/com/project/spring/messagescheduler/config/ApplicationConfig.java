package com.project.spring.messagescheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Timer;

@Configuration
public class ApplicationConfig {
    @Bean
    public Timer getTimer(){
        return new Timer();
    }
}
