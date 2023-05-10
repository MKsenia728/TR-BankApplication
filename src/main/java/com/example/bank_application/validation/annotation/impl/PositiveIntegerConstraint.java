package com.example.bank_application.validation.annotation.impl;

import com.example.bank_application.validation.annotation.PositiveInteger;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class PositiveIntegerConstraint implements ConstraintValidator<PositiveInteger, String> {

    private static final String POSITIVE_INTEGER = "\\d{0,15}";
    @Override
    public void initialize(PositiveInteger constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String integerId, ConstraintValidatorContext constraintValidatorContext) {

        return Optional.of(integerId)
                .filter(s -> !s.isBlank())
                .map(Integer::parseInt)
                .map(s -> s.toString().matches(POSITIVE_INTEGER))
                .orElse(false);
    }
}
