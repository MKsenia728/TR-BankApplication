package com.example.bank_application.controller;

import com.example.bank_application.dto.accountDto.*;
import com.example.bank_application.service.interf.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    public final AccountService accountService;

    @GetMapping("/id/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto getAccountById(@PathVariable("accountId") String accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public AccountsListDto getAllAccounts() {
        return accountService.getAllAccounts();
    }

    //    Account  getAllAccountWhereStatusIs Active/Remote/Pending/
    @GetMapping("/all/{status}")
    @ResponseStatus(HttpStatus.OK)
    public AccountsListDto getAllAccounts(@PathVariable("status") String status) {
        return accountService.getAllAccountsByStatus(status);
    }

    @PostMapping("new/client_tax/{clientTaxCode}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountAfterCreateDto createNewAccount(@RequestBody AccountCreateDto accountCreateDto, @PathVariable("clientTaxCode") String clientTaxCode) {
        return accountService.createNewAccount(accountCreateDto, clientTaxCode);
    }

    //    Blocked  findAccountsWhereProductIdIs....AndStatusIs.....
    @PutMapping("block_account/{productId}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public AccountsListAfterCreateDto blockAccountByProductIdAndStatus(
            @PathVariable("productId") String productId,
            @PathVariable("status") String status) {
        return accountService.blockAccountByProductIdAndStatus(productId, status);
    }
}
