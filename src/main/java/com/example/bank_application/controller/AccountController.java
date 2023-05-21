package com.example.bank_application.controller;

import com.example.bank_application.dto.AccountAfterCreateDto;
import com.example.bank_application.dto.AccountCreateDto;
import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.dto.AccountNameDto;
import com.example.bank_application.service.interf.AccountService;
import com.example.bank_application.validation.annotation.EnumAccountStatusOrNull;
import com.example.bank_application.validation.annotation.Iban;
import com.example.bank_application.validation.annotation.PositiveInteger;
import com.example.bank_application.validation.annotation.Uuid;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    public final AccountService accountService;

    @GetMapping("/id/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto getAccountById(@Uuid @PathVariable("accountId") String accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping("/name/{accountName}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto getAccountByName(@Iban @PathVariable("accountName") String accountName) {
        return accountService.getAccountByName(accountName);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountNameDto> getAllNameAccounts() {
        return accountService.getAllAccounts();
    }

    //    Account  getAllAccountWhereStatusIs Active/Remote/Pending/
    @GetMapping("/all/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> getAllAccountsByStatus(@EnumAccountStatusOrNull @PathVariable("status") String status) {
        return accountService.getAllAccountsByStatus(status);
    }

//    Create new account by given client tax code.

    @PostMapping("new/client_tax/{clientTaxCode}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountAfterCreateDto createNewAccount(
            @Valid
            @RequestBody AccountCreateDto accountCreateDto,
            @PathVariable("clientTaxCode") String clientTaxCode) {
        return accountService.createNewAccount(accountCreateDto, clientTaxCode);
    }

    //    Blocked  findAccountsWhereProductIdIs....AndStatusIs.....
    @PutMapping("block_account/{productId}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountAfterCreateDto> blockAccountByProductIdAndStatus(
            @PositiveInteger @PathVariable("productId") String productId,
            @EnumAccountStatusOrNull @PathVariable("status") String status) {
        return accountService.blockAccountByProductIdAndStatus(productId, status);
    }
}
