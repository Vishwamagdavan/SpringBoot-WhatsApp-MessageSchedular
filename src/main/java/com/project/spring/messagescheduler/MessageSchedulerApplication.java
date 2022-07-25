package com.project.spring.messagescheduler;

import com.project.spring.messagescheduler.utils.MessageSchedulerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Timer;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class MessageSchedulerApplication implements CommandLineRunner {
    @Autowired
    private Timer timer;
    @Autowired
    private MessageSchedulerTask task;

    public static void main(String[] args) {
        SpringApplication.run(MessageSchedulerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        timer.schedule(task,1000,3000);
    }
}
