package com.project.spring.messagescheduler.annotations.validators;

import com.project.spring.messagescheduler.annotations.CustomDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomDateValidator implements ConstraintValidator<CustomDate,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
