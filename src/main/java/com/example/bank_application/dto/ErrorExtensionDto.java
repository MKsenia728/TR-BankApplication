package com.example.bank_application.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorExtensionDto {
    String code;
    String message;
}
