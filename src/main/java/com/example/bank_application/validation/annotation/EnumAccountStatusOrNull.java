package com.example.bank_application.validation.annotation;

import com.example.bank_application.validation.annotation.impl.EnumAccountStatusConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EnumAccountStatusConstraint.class})
public @interface EnumAccountStatusOrNull {
    String message() default "Invalid account status entered";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
