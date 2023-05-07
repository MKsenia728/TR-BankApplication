package com.example.bank_application.service.interf;

import com.example.bank_application.dto.accountDto.*;

import java.util.List;

public interface AccountService {

    AccountDto getAccountById(String id);

    List<AccountDto> getAllAccounts();
    List<AccountDto> getAllAccountsByStatus(String Status);

    AccountAfterCreateDto createNewAccount(AccountCreateDto accountCreateDto, String clientTaxCode);

    List<AccountAfterCreateDto> blockAccountByProductIdAndStatus(String productId, String status);
}
