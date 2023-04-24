package com.example.bank_application.mapper;

import com.example.bank_application.dto.accountDto.AccountDto;
import com.example.bank_application.entity.Account;
import com.example.bank_application.mapper.impl.AccountMapperImpl;
import com.example.bank_application.service.util.DtoCreator;
import com.example.bank_application.service.util.EntityCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Account mapper test class")
class AccountMapperTest {
    private final AccountMapper accountMapper = new AccountMapperImpl();

    @Test
    void toDtoTest() {
        Account account = EntityCreator.getAccountEntity();
        AccountDto expectedAccountDto = DtoCreator.getAccountDto();
        Assertions.assertEquals(expectedAccountDto, accountMapper.toDto(account));
    }

    @Test
    void toDtoAfterCreateTest() {
    }

    @Test
    void toEntityTest() {
    }

    @Test
    void toListDtoTest() {
    }

    @Test
    void toListAfterCreateDtoTest() {
    }
}