package com.example.bank_application.mapper;

import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring", uses = UuidMapper.class)
public interface AccountMapper {
    AccountDto toDto(Account account);

    Account toEntity(AccountDto accountDto);

//    List<AccountDto> accountsToDto(List<Account> accounts);
}
