package com.example.bank_application.service.interf;

import com.example.bank_application.dto.accountDto.*;

public interface AccountService {

    AccountDto getAccountById(String id);

    AccountsListDto getAllAccounts();
    AccountsListDto getAllAccountsByStatus(String Status);

    AccountAfterCreateDto createNewAccount(AccountCreateDto accountCreateDto, String clientTaxCode);

    AccountsListAfterCreateDto blockAccountByProductIdAndStatus(String productId, String status);
}
