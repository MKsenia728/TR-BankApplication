package com.example.bank_application.dto;

import lombok.Value;

@Value
public class AccountCreateDto {
    String id;

    String name;

    String type;

    String currencyCode;

    String clientId;
}
