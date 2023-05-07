package com.example.bank_application.mapper.impl;

import com.example.bank_application.dto.accountDto.AccountAfterCreateDto;
import com.example.bank_application.dto.accountDto.AccountCreateDto;
import com.example.bank_application.dto.accountDto.AccountDto;
import com.example.bank_application.entity.Account;
import com.example.bank_application.entity.Client;
import com.example.bank_application.entity.enums.AccountStatus;
import com.example.bank_application.entity.enums.AccountType;
import com.example.bank_application.entity.enums.CurrencyType;
import com.example.bank_application.mapper.AccountMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapperImpl implements AccountMapper {

    public AccountDto toDto(Account account) {
        if (account == null) {
            return null;
        } else {
            String clientFirstName;
            String clientLastName;
            String id;
            String name;
            String type = null;
            String status = null;
            String balance = null;
            String currencyCode = null;
            LocalDateTime createdAt;
            LocalDateTime updatedAt;
            clientFirstName = this.accountClientFirstName(account);
            clientLastName = this.accountClientLastName(account);
            id = (account.getId()).toString();
            name = account.getName();
            if (account.getType() != null) {
                type = account.getType().name();
            }

            if (account.getStatus() != null) {
                status = account.getStatus().name();
            }

            if (account.getBalance() != null) {
                balance = String.valueOf(account.getBalance());
            }

            if (account.getCurrencyCode() != null) {
                currencyCode = account.getCurrencyCode().name();
            }

            createdAt = account.getCreatedAt();
            updatedAt = account.getUpdatedAt();
            return new AccountDto(id, name, clientFirstName, clientLastName, type, status, balance, currencyCode, createdAt, updatedAt);
        }
    }

@Override
    public AccountAfterCreateDto toDtoAfterCreate(Account account) {
        if (account == null) {
            return null;
        } else {
            String name;
            String type = null;
            String status = null;
            String balance = null;
            String currencyCode = null;
            LocalDateTime createdAt;
            LocalDateTime updatedAt;
            name = account.getName();
            if (account.getType() != null) {
                type = account.getType().name();
            }

            if (account.getStatus() != null) {
                status = account.getStatus().name();
            }

            if (account.getBalance() != null) {
                balance = String.valueOf(account.getBalance());
            }

            if (account.getCurrencyCode() != null) {
                currencyCode = account.getCurrencyCode().name();
            }

            createdAt = account.getCreatedAt();
            updatedAt = account.getUpdatedAt();
            return new AccountAfterCreateDto(name, type, status, balance, currencyCode, createdAt, updatedAt);
        }
    }
@Override
    public Account toEntity(AccountCreateDto accountCreateDto) {
        if (accountCreateDto == null) {
            return null;
        } else {
            Account account = new Account();
            account.setName(accountCreateDto.getName());
            if (accountCreateDto.getType() != null) {
                account.setType(Enum.valueOf(AccountType.class, accountCreateDto.getType()));
            }

            if (accountCreateDto.getStatus() != null) {
                account.setStatus(Enum.valueOf(AccountStatus.class, accountCreateDto.getStatus()));
            }

            if (accountCreateDto.getBalance() != null) {
                account.setBalance(Double.parseDouble(accountCreateDto.getBalance()));
            }

            account.setCurrencyCode(Enum.valueOf(CurrencyType.class, accountCreateDto.getCurrencyCode()));

            account.setCreatedAt(LocalDateTime.now());
            account.setUpdatedAt(LocalDateTime.now());
            return account;
        }
    }
@Override
    public List<AccountDto> toListDto(List<Account> accounts) {
        if (accounts == null) {
            return null;
        } else {
            List<AccountDto> list = new ArrayList<>(accounts.size());

            for (Account account : accounts) {
                list.add(this.toDto(account));
            }

            return list;
        }
    }
@Override
    public List<AccountAfterCreateDto> toListAfterCreateDto(List<Account> accounts) {
        if (accounts == null) {
            return null;
        } else {
            List<AccountAfterCreateDto> list = new ArrayList<>(accounts.size());

            for (Account account : accounts) {
                list.add(this.toDtoAfterCreate(account));
            }

            return list;
        }
    }

    private String accountClientFirstName(Account account) {
        if (account == null) {
            return null;
        } else {
            Client client = account.getClient();
            if (client == null) {
                return null;
            } else {
                return client.getFirstName();
            }
        }
    }

    private String accountClientLastName(Account account) {
        if (account == null) {
            return null;
        } else {
            Client client = account.getClient();
            if (client == null) {
                return null;
            } else {
                return client.getLastName();
            }
        }
    }
}
