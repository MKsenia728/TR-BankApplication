package com.example.bank_application.controller;

import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.dto.AccountsListDto;
import com.example.bank_application.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    public final AccountService accountService;

    @GetMapping("s/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto getAccountById(@PathVariable("accountId") String accountId) {
        return accountService.getAccountById(accountId);
    }

//    @GetMapping("/all")
//    @ResponseStatus(HttpStatus.OK)
//    public AccountsListDto getAllAccounts() {
//        return accountService.getAllAccounts();
//    }

}
