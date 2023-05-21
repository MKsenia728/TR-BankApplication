package com.example.bank_application.validation.annotation.impl;

import com.example.bank_application.validation.annotation.PositiveDecimalOrNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class PositiveDecimalOrNullConstraint implements ConstraintValidator<PositiveDecimalOrNull, String> {

    private static final String POSITIVE_DECIMAL_PATTERN = "\\d{0,15}(\\.\\d{0,2})?";
    @Override
    public void initialize(PositiveDecimalOrNull constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return Optional.of(value)
                .filter(s -> !s.isBlank())
                .map(s -> s.matches(POSITIVE_DECIMAL_PATTERN))
                .orElse(false);
    }
}
