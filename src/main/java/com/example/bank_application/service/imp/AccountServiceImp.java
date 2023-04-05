package com.example.bank_application.service.imp;

import com.example.bank_application.dto.AccountCreateDto;
import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.dto.AccountsListDto;
import com.example.bank_application.entity.Account;
import com.example.bank_application.entity.Client;
import com.example.bank_application.entity.enums.AccountStatus;
import com.example.bank_application.mapper.AccountMapper;
import com.example.bank_application.repository.AccountRepository;
import com.example.bank_application.repository.ClientRepository;
import com.example.bank_application.service.AccountService;
import com.example.bank_application.service.exceptions.AccountAlreadyExistException;
import com.example.bank_application.service.exceptions.AccountNotFoundException;
import com.example.bank_application.service.exceptions.ClientNotFoundByTaxCodeException;
import com.example.bank_application.service.exceptions.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    @Override
    @Transactional(readOnly = true)
    public AccountDto getAccountById(String id) {
        return accountMapper.toDto(accountRepository.findAccountById(UUID.fromString(id)).orElseThrow(
                () -> new AccountNotFoundException((ErrorMessage.ACCOUNT_NOT_FOUND))));
    }

    @Override
    @Transactional
    public AccountsListDto getAllAccountsByStatusActive() {
        return new AccountsListDto(accountMapper.ToListDto(accountRepository.getAllByStatus(AccountStatus.ACTIVE)));
    }

    @Override
    public void createNewAccount(AccountCreateDto accountCreateDto, String clientTaxCode) {
        Client client = clientRepository.findClientByTaxCode(clientTaxCode);
        if (client == null) {
            throw new ClientNotFoundByTaxCodeException(ErrorMessage.CLIENT_NOT_FOUND_BY_TAX_CODE);
        } else if (accountRepository.findAccountByName(accountCreateDto.getName()) != null) {
            throw new AccountAlreadyExistException(ErrorMessage.ACCOUNT_ALREADY_EXISTS);
        } else {
            Account account = accountMapper.toEntity(accountCreateDto);
            System.out.println(client);
            account.setClient(client);
            System.out.println(account);
            accountRepository.save(account);
        }
    }
}

