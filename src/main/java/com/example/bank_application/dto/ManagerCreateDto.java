package com.example.bank_application.dto;

import com.example.bank_application.validation.annotation.FirstLastName;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class ManagerCreateDto {
    @NotNull
    @FirstLastName
    String firstName;

    @NotNull
    @FirstLastName
    String lastName;

}
