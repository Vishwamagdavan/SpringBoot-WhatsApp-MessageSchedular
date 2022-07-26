package com.project.spring.messagescheduler.annotations.validators;

import com.project.spring.messagescheduler.annotations.CustomDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.SimpleFormatter;

public class CustomDateValidator implements ConstraintValidator<CustomDate, String> {

    @Override
    public boolean isValid(String timestamp, ConstraintValidatorContext constraintValidatorContext) {
        // Check the format of
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            format.parse(timestamp);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
