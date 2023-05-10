package com.example.bank_application.validation.annotation.impl;

import com.example.bank_application.validation.annotation.Iban;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class IbanConstraint implements ConstraintValidator<Iban, String> {

    private static final String IBAN = "[A-Z]{2}\\s*\\d{2}\\s*\\d{6}\\s*\\d{14}";

    @Override
    public void initialize(Iban constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String accountName, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.of(accountName)
                .filter(s -> !s.isBlank())
                .map(s -> s.matches(IBAN))
                .orElse(false);
    }
}
