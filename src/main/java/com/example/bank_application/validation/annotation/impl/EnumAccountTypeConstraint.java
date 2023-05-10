package com.example.bank_application.validation.annotation.impl;

import com.example.bank_application.entity.enums.AccountStatus;
import com.example.bank_application.entity.enums.AccountType;
import com.example.bank_application.validation.annotation.EnumAccountTypeOrNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumAccountTypeConstraint implements ConstraintValidator<EnumAccountTypeOrNull, String> {
    @Override
    public void initialize(EnumAccountTypeOrNull constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String type, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;
        if (type == null) {
            result = true;
        } else
            try {
                AccountType.valueOf(type);
                result = true;
            } catch (RuntimeException e) {
                result = false;
            }
        return result;
    }
}
