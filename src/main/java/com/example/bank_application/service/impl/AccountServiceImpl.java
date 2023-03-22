package com.example.bank_application.service.impl;

import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.dto.AccountListDto;
import com.example.bank_application.entity.enums.AccountStatus;
import com.example.bank_application.mapper.AccountMapper;
import com.example.bank_application.repository.AccountRepository;
import com.example.bank_application.service.AccountService;
import com.example.bank_application.service.exceptions.AccountNotFoundException;
import com.example.bank_application.service.exceptions.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl
//        implements AccountService
{
//    private final AccountRepository accountRepository;
//    private final AccountMapper accountMapper;
//
//
//    @Override
//    @Transactional(readOnly = true)
//    public AccountDto getAccountById(UUID id) {
////        log.info("Get account with id {}", id);
//        return accountMapper.toDto(accountRepository.findAccountById(id).orElseThrow(
//                () -> new AccountNotFoundException
//                        ((ErrorMessage.ACCOUNT_NOT_FOUND))));
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public AccountListDto getAllAccounts() {
////        log.info("Get all active accounts");
//        return new AccountListDto(accountMapper.accountsToAccountsDto(accountRepository.getAccountsByStatus(AccountStatus.valueOf("ACTIVE"))));
//    }
}
