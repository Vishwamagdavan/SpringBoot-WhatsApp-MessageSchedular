package com.project.spring.messagescheduler.annotations;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface PhoneNumber {
    String message() default "Invalid Phone Number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
