package com.project.spring.messagescheduler.annotations;

import com.project.spring.messagescheduler.annotations.validators.CustomDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CustomDateValidator.class)
public @interface CustomDate {
    String message() default "Invalid date format, format yyyy-MM-dd HH:mm:ss";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
