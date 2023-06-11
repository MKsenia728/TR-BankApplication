package com.example.bank_application.dto;

import java.util.List;

public record ErrorResponseDto(String errorCode, List<ErrorExtensionDto> errorExtensions) {
}
