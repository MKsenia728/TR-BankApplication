package com.example.bank_application.service.interf;

import com.example.bank_application.dto.AccountAfterCreateDto;
import com.example.bank_application.dto.AccountCreateDto;
import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.dto.AccountNameDto;

import java.util.List;

public interface AccountService {

    AccountDto getAccountById(String id);
    AccountDto getAccountByName(String name);

    List<AccountNameDto> getAllAccounts();
    List<AccountDto> getAllAccountsByStatus(String Status);

    AccountAfterCreateDto createNewAccount(AccountCreateDto accountCreateDto, String clientTaxCode);

    List<AccountAfterCreateDto> blockAccountByProductIdAndStatus(String productId, String status);
}
