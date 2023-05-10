package com.example.bank_application.dto;

import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponseDto {
    String errorCode;
    List<ErrorExtensionDto> errorExtensions;
}
