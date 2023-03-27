package com.example.bank_application.mapper;

import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.entity.Account;

import java.util.List;

public interface AccountMapper {
    AccountDto toDto(Account account);

    Account toEntity(AccountDto accountDto);

    List<AccountDto> accountsToDto(List<Account> accounts);
}
