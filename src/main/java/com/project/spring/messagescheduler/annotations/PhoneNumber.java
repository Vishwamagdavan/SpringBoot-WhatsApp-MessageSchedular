package com.project.spring.messagescheduler.annotations;

import com.project.spring.messagescheduler.annotations.validators.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {
    String message() default "Invalid Phone Number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
