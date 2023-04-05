package com.example.bank_application.mapper;

import com.example.bank_application.dto.AccountCreateDto;
import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.entity.Account;
import com.example.bank_application.entity.Client;
import com.example.bank_application.entity.enums.AccountStatus;
import com.example.bank_application.entity.enums.AccountType;
import com.example.bank_application.entity.enums.ManagerStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", uses = UuidMapper.class,  injectionStrategy = CONSTRUCTOR, imports = {LocalDateTime.class, AccountStatus.class, AccountType.class})
public interface AccountMapper {
    @Mapping(source = "account.client.firstName", target="clientFirstName")
    @Mapping(source = "account.client.lastName", target="clientLastName")
    AccountDto toDto(Account account);

    Account toEntity(AccountDto accountDto);

    @Mapping(target = "status", expression = "java(AccountStatus.PENDING)")
    @Mapping(target = "type", expression = "java(AccountType.CURRENT)")
    @Mapping(target = "balance", expression = "java(0.00)")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    Account toEntity(AccountCreateDto accountCreateDto);

    List<AccountDto> ToListDto(List<Account> accounts);
}
