package com.example.bank_application.validation.annotation;

import com.example.bank_application.validation.annotation.impl.IbanConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IbanConstraint.class})
public @interface Iban {
    String message() default "Account name must be IBAN";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

