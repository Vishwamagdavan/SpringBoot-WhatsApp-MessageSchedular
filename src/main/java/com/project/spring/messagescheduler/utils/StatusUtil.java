package com.project.spring.messagescheduler.utils;

import org.springframework.stereotype.Component;

@Component
public class StatusUtil {
    /**
     * This method takes the input as status and returns as integer
     * @param status CREATED,PENDING,SENT
     * @return returns if the invalid status code is passed
     */
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
