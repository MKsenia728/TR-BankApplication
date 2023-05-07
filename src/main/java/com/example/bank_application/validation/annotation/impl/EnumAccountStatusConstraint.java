package com.example.bank_application.validation.annotation.impl;

import com.example.bank_application.entity.enums.AccountStatus;
import com.example.bank_application.validation.annotation.EnumAccountStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumAccountStatusConstraint implements ConstraintValidator<EnumAccountStatus, String> {

    @Override
    public void initialize(EnumAccountStatus constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String status, ConstraintValidatorContext constraintValidatorContext) {
        try {
            AccountStatus.valueOf(status.toUpperCase());
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
