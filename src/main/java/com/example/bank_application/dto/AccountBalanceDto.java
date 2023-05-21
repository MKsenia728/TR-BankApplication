package com.example.bank_application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class AccountBalanceDto {
    String balance;

    String currencyCode;

}
