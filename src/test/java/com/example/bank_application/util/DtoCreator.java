package com.example.bank_application.util;

import com.example.bank_application.dto.*;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class DtoCreator {

    public static final String NAME = "TT 89 311045 00234355921201";

    public static AccountDto getAccountDto() {
        return new AccountDto(
                "00001d7f-d14f-4655-9399-25bf27b16588",
                NAME,
                "Ivan",
                "Tester",
                "CURRENT",
                "ACTIVE",
                "10000.0",
                "EUR",
                LocalDateTime.of(2021, 12, 2, 9, 0, 0),
                LocalDateTime.of(2021, 12, 2, 9, 0, 0)
        );
    }

    public static AccountNameDto getAccountNameDto() {
        return new AccountNameDto(
                NAME
        );
    }

    public static AccountAfterCreateDto getAccountAfterCreateDto(String status) {
        return new AccountAfterCreateDto(
                NAME,
                "CURRENT",
                status,
                "10000.0",
                "EUR",
                LocalDateTime.of(2021, 12, 2, 9, 0, 0),
                LocalDateTime.of(2021, 12, 2, 9, 0, 0)
        );
    }


    public static AccountCreateDto getAccountCreateDto() {
        return new AccountCreateDto(
                NAME,
                null,
                null,
                "10000.0",
                "EUR"
        );
    }

    public static AccountCreateDto getAccountCreateDtoWithoutNecessaryData() {
        return new AccountCreateDto(
                null,
                null,
                null,
                "10000.0",
                "EUR"
        );
    }

    public static AccountCreateDto getAccountCreateDtoWithInvalidData() {
        return new AccountCreateDto(
                "TT 89 311045 0023435592120",
                "CREDI",
                "",
                "1a",
                "EURo"
        );
    }

    public static List<AccountBalanceDto> getAccountsBalanceDto() {
        List<AccountBalanceDto> list = new ArrayList<>();
        list.add(new AccountBalanceDto("10000.0", "EUR"));
        return list;
    }



    public static ClientWithBalanceDto getClientWithBalanceDto() {
        return new ClientWithBalanceDto(
                "ACTIVE",
                "123123123123",
                "Ivan",
                "Tester",
                "ivan@gmail.com",
                "+111111111111111",
                getAccountsBalanceDto()
        );
    }

    public static ClientDto getClientDto() {
        return new ClientDto(
                "00011d7f-d14f-4655-9399-25bf27b16588",
                "ACTIVE",
                "123123123123",
                "Ivan",
                "Tester",
                "ivan@gmail.com",
                "Berlin",
                "+111111111111111",
                LocalDateTime.of(2021, 12, 2, 9, 0, 0),
                LocalDateTime.of(2021, 12, 2, 9, 0, 0)
        );
    }

    public static ManagerDto getManagerDto() {
        return new ManagerDto(
                "1",
                "Petr",
                "Popov",
                "ACTIVE",
                LocalDateTime.of(2021, 12, 2, 9, 0, 0),
                LocalDateTime.of(2021, 12, 2, 9, 0, 0)
        );
    }

    public static ManagerCreateDto getManagerCreateDto() {
        return new ManagerCreateDto(
                "Petr",
                "Popov"
        );
    }

    public static ManagerAfterCreateDto getManagerAfterCreateDto() {
        return new ManagerAfterCreateDto(
                "1",
                "Petr",
                "Popov",
                "PENDING",
                LocalDateTime.of(2021, 12, 2,9,0,0),
                LocalDateTime.of(2021, 12, 2, 9, 0, 0)
        );
    }
}
