package com.example.bank_application.mapper;

import com.example.bank_application.dto.*;
import com.example.bank_application.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR, imports = {LocalDateTime.class})

public interface AccountMapper {

    @Mapping(source = "account.client.firstName", target = "clientFirstName")
    @Mapping(source = "account.client.lastName", target = "clientLastName")
    AccountDto toDto(Account account);

    AccountBalanceDto toDtoBalanceAndCurrency(Account account);

    AccountAfterCreateUpdateDto toDtoAfterCreate(Account account);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    Account toEntity(AccountCreateDto accountCreateDto);

    List<AccountDto> toListDto(List<Account> accounts);

    List<AccountNameDto> toListDtoName(List<Account> accounts);

    List<AccountAfterCreateUpdateDto> toListAfterCreateDto(List<Account> accounts);
}