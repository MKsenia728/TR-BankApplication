package com.example.bank_application.dto;

import lombok.Value;

import java.util.List;

@Value
public class ClientWithBalanceDto {
    String status;

    String taxCode;

    String firstName;

    String lastName;

    String email;

    String phone;

    List<AccountBalanceDto> balanceAndCurrency;

}
