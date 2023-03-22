package com.example.bank_application.service;

import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.dto.AccountListDto;

import java.util.UUID;

public interface AccountService {
    AccountDto getAccountById(UUID id);

    AccountListDto getAllAccounts();
}
