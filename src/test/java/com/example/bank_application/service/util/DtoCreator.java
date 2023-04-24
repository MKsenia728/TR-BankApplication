package com.example.bank_application.service.util;

import com.example.bank_application.dto.accountDto.AccountAfterCreateDto;
import com.example.bank_application.dto.accountDto.AccountCreateDto;
import com.example.bank_application.dto.accountDto.AccountDto;
import com.example.bank_application.dto.accountDto.AccountsListDto;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class DtoCreator {
    public static AccountDto getAccountDto() {
        return new AccountDto(
                "00001d7f-d14f-4655-9399-25bf27b16588",
                "TT 89 311045 00234355921201",
                "Ivan",
                "Tester",
                "CURRENT",
                "ACTIVE",
                "10000",
                "EUR",
                LocalDateTime.of(2021,12,2,9,0,0),
                LocalDateTime.of(2021,12,2,9,0,0)
        );
    }

    public static AccountAfterCreateDto getAccountAfterCreateDto() {
        return new AccountAfterCreateDto(
                "TT 89 311045 00234355921201",
                "CURRENT",
                "ACTIVE",
                "10000",
                "EUR",
                LocalDateTime.of(2021,12,2,9,0,0),
                LocalDateTime.of(2021,12,2,9,0,0)
        );
    }

    public static AccountCreateDto getAccountCreateDto() {
        return new AccountCreateDto(
                "TT 89 311045 00234355921201",
                null,
                null,
                "10000",
                "EUR"
        );
    }

//    public static AccountsListDto getAccountsListLto() {
//        List<AccountDto> list = new ArrayList<>();
//        list.add(getAccountDto());
//        return list;
//    }
}
