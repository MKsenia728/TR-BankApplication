package com.example.bank_application.validation.annotation.impl;

import com.example.bank_application.entity.enums.AccountStatus;
import com.example.bank_application.entity.enums.CurrencyType;
import com.example.bank_application.validation.annotation.EnumCurrencyType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumCurrencyTypeConstraint implements ConstraintValidator<EnumCurrencyType, String> {


    @Override
    public void initialize(EnumCurrencyType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String currencyType, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;
        if (currencyType == null) {
            result = true;
        } else
            try {
                CurrencyType.valueOf(currencyType.toUpperCase());
                result = true;
            } catch (RuntimeException e) {
                result = false;
            }
        return result;
    }
}
