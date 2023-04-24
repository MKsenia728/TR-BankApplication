package com.example.bank_application.service.util;

import com.example.bank_application.entity.Account;
import com.example.bank_application.entity.Agreement;
import com.example.bank_application.entity.Client;
import com.example.bank_application.entity.Manager;
import com.example.bank_application.entity.enums.AccountStatus;
import com.example.bank_application.entity.enums.AccountType;
import com.example.bank_application.entity.enums.ClientStatus;
import com.example.bank_application.entity.enums.CurrencyType;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

@UtilityClass
public class EntityCreator {

    public static final String UUID = "00001d7f-d14f-4655-9399-25bf27b16588";

    public static Account getAccountEntity() {
        return new Account(
                java.util.UUID.fromString("00001d7f-d14f-4655-9399-25bf27b16588"),
                "TT 89 311045 00234355921201",
                AccountType.CURRENT,
                AccountStatus.ACTIVE,
                10000.0,
                CurrencyType.EUR,
                LocalDateTime.of(2021,12,2,9,0,0),
                LocalDateTime.of(2021,12,2,9,0,0),
                getClientEntity(),
                null,
                null,
                new Agreement()
                );
   }

   public static Client getClientEntity() {
       return new Client(
               java.util.UUID.fromString("0011d7f-d14f-4655-9399-25bf27b16588"),
               ClientStatus.ACTIVE,
               "123123123123",
               "Ivan",
               "Tester",
               "ivan@gmail.com",
               "Berlin",
               "+111111111111111",
               LocalDateTime.of(2021,12,2,9,0,0),
               LocalDateTime.of(2021,12,2,9,0,0),
               new HashSet<Account>(),
               new Manager()
       );
   }
}
