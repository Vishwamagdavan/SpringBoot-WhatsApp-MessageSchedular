package com.project.spring.messagescheduler.annotations.validators;

import com.project.spring.messagescheduler.annotations.CustomDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomDateValidator implements ConstraintValidator<CustomDate, Timestamp> {

    @Override
    public boolean isValid(Timestamp timestamp, ConstraintValidatorContext constraintValidatorContext) {
        return timestamp != null;
    }
}
