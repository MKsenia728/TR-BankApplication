package com.example.bank_application.dto;

import com.example.bank_application.validation.annotation.Iban;

public record AccountNameDto(@Iban String name) {
}
