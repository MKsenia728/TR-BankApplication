package com.example.bank_application.validation.annotation.impl;

import com.example.bank_application.validation.annotation.Uuid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class UuidConstraint implements ConstraintValidator<Uuid, String > {

    private static final String UUID = "^[\\da-fA-F]{8}-[\\da-fA-F]{4}-[\\da-fA-F]{4}-[\\da-fA-F]{4}-[\\da-fA-F]{12}$";

    @Override
    public void initialize(Uuid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


    @Override
    public boolean isValid(String uuid, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(uuid)
                .filter(s -> !s.isBlank())
                .map(s -> s.matches(UUID))
                .orElse(false);
    }
}
