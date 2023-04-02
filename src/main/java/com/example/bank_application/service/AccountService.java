package com.example.bank_application.service;

import com.example.bank_application.dto.AccountCreateDto;
import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.dto.AccountsListDto;

public interface AccountService {

    AccountDto getAccountById(String id);

   AccountsListDto getAllAccountsByStatusActive();

    void createNewAccount(AccountCreateDto accountCreateDto, String clientTaxCode);
}
