package com.example.bank_application.dto.accountDto;

import lombok.Value;

import java.util.List;

@Value
public class AccountsListDto {
    List<AccountDto> accountDtoList;
}
