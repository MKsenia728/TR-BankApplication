package com.example.bank_application.controller;

import com.example.bank_application.dto.AccountCreateDto;
import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.dto.AccountsListDto;
import com.example.bank_application.service.AccountService;
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

    @GetMapping("/all/active")
    @ResponseStatus(HttpStatus.OK)
    public AccountsListDto getAllAccounts() {
        return accountService.getAllAccountsByStatusActive();
    }

    @PostMapping("new/clientTax/{clientTaxCode}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewAccount(@RequestBody AccountCreateDto accountCreateDto, @PathVariable("clientTaxCode") String clientTaxCode) {
        accountService.createNewAccount(accountCreateDto, clientTaxCode);
    }
}
