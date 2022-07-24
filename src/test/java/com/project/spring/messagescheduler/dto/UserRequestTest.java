package com.project.spring.messagescheduler.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestTest {
    private Validator validator;
    @BeforeEach
    public void setUpValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void whenNotNullName_thenNoConstraintViolations() {
        UserRequest user = new UserRequest("Vishva");
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(user);
        assertEquals(violations.size(),0);
    }

    @Test
    public void whenNullName_thenOneConstraintViolation() {
        UserRequest user = new UserRequest(null);
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(user);
        assertEquals(violations.size(),1);
    }

    @Test
    public void whenEmptyName_thenNoConstraintViolations() {
        UserRequest user = new UserRequest("");
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(user);
        assertEquals(violations.size(),1);
    }
}