package com.example.bank_application.validation.annotation.impl;

import com.example.bank_application.entity.enums.AccountStatus;
import com.example.bank_application.validation.annotation.EnumAccountStatus;
import com.example.bank_application.validation.annotation.EnumAccountType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumAccountTypeConstraint implements ConstraintValidator<EnumAccountType, String> {
    @Override
    public void initialize(EnumAccountType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String type, ConstraintValidatorContext constraintValidatorContext) {
        try {
            AccountStatus.valueOf(type.toUpperCase());
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
