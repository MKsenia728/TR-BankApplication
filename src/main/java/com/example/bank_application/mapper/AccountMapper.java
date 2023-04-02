package com.example.bank_application.mapper;

import com.example.bank_application.dto.AccountCreateDto;
import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.entity.Account;
import com.example.bank_application.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = UuidMapper.class)
public interface AccountMapper {
    @Mapping(source = "account.client.firstName", target="clientFirstName")
    @Mapping(source = "account.client.lastName", target="clientLastName")
    AccountDto toDto(Account account);

    Account toEntity(AccountDto accountDto);
    @Mapping(source = "client", target = "client")
    Account toEntity(AccountCreateDto accountCreateDto, Client client);

    List<AccountDto> ToListDto(List<Account> accounts);
}
