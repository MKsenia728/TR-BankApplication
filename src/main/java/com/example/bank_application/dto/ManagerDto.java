package com.example.bank_application.dto;

import com.example.bank_application.validation.annotation.PositiveInteger;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ManagerDto(
        @PositiveInteger
        String id,

        String firstName,

        String lastName,

        String status,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime createdAt,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime updatedAt) {
}
