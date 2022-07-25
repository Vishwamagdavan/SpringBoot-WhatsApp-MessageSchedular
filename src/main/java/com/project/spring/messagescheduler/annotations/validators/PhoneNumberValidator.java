package com.project.spring.messagescheduler.annotations.validators;

import com.project.spring.messagescheduler.annotations.PhoneNumber;
import com.project.spring.messagescheduler.utils.ApplicationParser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber,String> {
    private final ApplicationParser applicationParser;

    public PhoneNumberValidator(ApplicationParser applicationParser) {
        this.applicationParser = applicationParser;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // Removing the extra space, + and - sign
        String phoneNumber=applicationParser.parsePhoneNumber(s);
        System.out.println(phoneNumber);
        Pattern pattern = Pattern.compile("(91)?[6-9][0-9]{11}");
        Matcher match = pattern.matcher(phoneNumber);
        return (match.find() && match.group().equals(phoneNumber));
    }
}
