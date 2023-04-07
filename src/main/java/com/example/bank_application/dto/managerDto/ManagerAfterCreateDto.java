package com.example.bank_application.dto.managerDto;

import com.example.bank_application.entity.enums.ManagerStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ManagerAfterCreateDto {
    String id;

    String firstName;

    String lastName;

    String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDateTime updatedAt;
}
