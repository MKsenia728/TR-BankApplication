package com.example.bank_application.validation.annotation;

import com.example.bank_application.validation.impl.PositiveIntegerConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({FIELD, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PositiveIntegerConstraint.class})
public @interface PositiveInteger {
    String message() default "Value must be integer and positive";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
