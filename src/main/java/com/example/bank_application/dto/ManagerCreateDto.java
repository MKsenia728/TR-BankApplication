package com.example.bank_application.dto;

import com.example.bank_application.validation.annotation.FirstLastName;
import jakarta.validation.constraints.NotNull;

public record ManagerCreateDto(
        @NotNull
        @FirstLastName
        String firstName,

        @NotNull
        @FirstLastName
        String lastName) {
}
