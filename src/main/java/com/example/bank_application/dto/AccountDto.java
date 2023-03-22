package com.example.bank_application.dto;

import lombok.Value;

@Value
public class AccountDto {
    String id;
    String name;
    String type;
    String status;
    String balance;
    String currencyCode;
//    String createdAt;
}
