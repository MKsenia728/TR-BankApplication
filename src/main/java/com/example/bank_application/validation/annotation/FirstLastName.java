package com.example.bank_application.validation.annotation;

import com.example.bank_application.validation.impl.IbanConstraint;
import com.example.bank_application.validation.impl.NameConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NameConstraint.class})
public @interface FirstLastName {
    String message() default "The name must start with a capital letter," +
            " at least 2 characters, only Latin or Russian letters, " +
            "apostrophe characters and one \"-\" character are allowed.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

