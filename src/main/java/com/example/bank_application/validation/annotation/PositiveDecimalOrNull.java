package com.example.bank_application.validation.annotation;

import com.example.bank_application.validation.annotation.impl.PositiveDecimalConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PositiveDecimalConstraint.class})
public @interface PositiveDecimalOrNull {
    String message() default "Balance must be positive";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
