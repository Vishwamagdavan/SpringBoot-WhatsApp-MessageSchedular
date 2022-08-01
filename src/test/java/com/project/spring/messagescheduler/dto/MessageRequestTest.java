package com.project.spring.messagescheduler.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MessageRequestTest {
    private Validator validator;
    @BeforeEach
    public void setUpInstanceForValidation() {
        validator= Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void whenAllTheFieldAreNULL(){
        MessageRequest messageRequest=new MessageRequest(null,null,null,null);
        Set<ConstraintViolation<MessageRequest>> violations = validator.validate(messageRequest);
        assertEquals(violations.size(),5);
    }

    @Test
    public void whenAllValidFields(){
        MessageRequest messageRequest=new MessageRequest("hi welcome to Whatsapp API",12L, "+919952862652",Timestamp.from(Instant.now()).toString());
        Set<ConstraintViolation<MessageRequest>> violations = validator.validate(messageRequest);
        assertEquals(violations.size(),0);
    }

    @Test
    public void whenDateNotValid(){
        MessageRequest messageRequest=new MessageRequest("hi welcome to Whatsapp API",12L, "+919952862652",null);
        Set<ConstraintViolation<MessageRequest>> violations = validator.validate(messageRequest);
        assertEquals(violations.size(),2);

    }
}