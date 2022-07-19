package com.project.spring.messagescheduler.utils;

import com.project.spring.messagescheduler.enums.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusUtil {
    public int getStatus(String status){
        if(status.equalsIgnoreCase("CREATED"))
            return 0;
        else if(status.equalsIgnoreCase("PENDING"))
            return 1;
        else if(status.equalsIgnoreCase("SENT"))
            return 2;
        return -1;
    }
}
