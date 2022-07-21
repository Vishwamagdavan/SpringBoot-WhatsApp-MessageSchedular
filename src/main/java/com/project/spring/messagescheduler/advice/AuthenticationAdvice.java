package com.project.spring.messagescheduler.advice;

import com.project.spring.messagescheduler.exceptions.AuthFailedException;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;
import com.project.spring.messagescheduler.service.AuthRequest;
import com.project.spring.messagescheduler.service.MessageService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthenticationAdvice {
    @Pointcut("execution(* com.project.spring.messagescheduler.controller.MessageController.*(..))")
    public void messageController(){

    }
    @Autowired
    AuthRequest authRequest;
    @Before("execution(* com.project.spring.messagescheduler.controller.MessageController.*(..)) && args(authToken)")
    public void isValidRequest(@RequestHeader(value ="auth_token", required = true) String authToken) throws AuthFailedException, ResourceNotFoundException {
        if(!authRequest.isValidToken(authToken)){
            throw new AuthFailedException("Unauthorized Request");
        }
    }

}
