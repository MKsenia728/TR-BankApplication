package com.example.bank_application.validation.impl;

import com.example.bank_application.entity.enums.AccountStatus;
import com.example.bank_application.validation.annotation.EnumAccountStatusOrNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumAccountStatusConstraint implements ConstraintValidator<EnumAccountStatusOrNull, String> {

    @Override
    public void initialize(EnumAccountStatusOrNull constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String status, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;
        if (status == null) {
            result = true;
        } else
            try {
                AccountStatus.valueOf(status);
                result = true;
            } catch (RuntimeException e) {
                result = false;
            }
        return result;
    }
}
