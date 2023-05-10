package com.example.bank_application.util;

import com.example.bank_application.dto.accountDto.AccountAfterCreateDto;
import com.example.bank_application.dto.accountDto.AccountCreateDto;
import com.example.bank_application.dto.accountDto.AccountDto;
import com.example.bank_application.dto.clientDto.ClientDto;
import com.example.bank_application.dto.clientDto.ClientWithBalanceDto;
import com.example.bank_application.dto.managerDto.ManagerAfterCreateDto;
import com.example.bank_application.dto.managerDto.ManagerCreateDto;
import com.example.bank_application.dto.managerDto.ManagerDto;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

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
                "10000.0",
                "EUR",
                LocalDateTime.of(2021, 12, 2, 9, 0, 0),
                LocalDateTime.of(2021, 12, 2, 9, 0, 0)
        );
    }

    public static AccountAfterCreateDto getAccountAfterCreateDto(String status) {
        return new AccountAfterCreateDto(
                "TT 89 311045 00234355921201",
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
                "TT 89 311045 00234355921201",
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

    public static ClientWithBalanceDto getClientWithBalanceDto() {
        return new ClientWithBalanceDto(
                "ACTIVE",
                "123123123123",
                "Ivan",
                "Tester",
                "ivan@gmail.com",
                "+111111111111111"
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
                "Afanasij",
                "Popov",
                "ACTIVE",
                LocalDateTime.of(2021,12,2,9,0,0),
                LocalDateTime.of(2021,12,2,9,0,0)
        );
    }

    public static ManagerCreateDto getManagerCreateDto() {
        return new ManagerCreateDto(
                "Afanasij",
                "Popov"
        );
    }

    public static ManagerAfterCreateDto getManagerAfterCreateDto() {
        return new ManagerAfterCreateDto(
                "1",
                "Afanasij",
                "Popov",
                "PENDING",
                LocalDateTime.of(2021,12,2,9,0,0),
                LocalDateTime.of(2021,12,2,9,0,0)
        );
    }
}
