package com.example.bank_application.dto.accountDto;

import com.example.bank_application.validation.annotation.*;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class AccountCreateDto {
    @NotNull
    @Iban
    String name;
    @EnumAccountTypeOrNull
    String type;
    @EnumAccountStatusOrNull
    String status;
    @PositiveDecimalOrNull
    String balance;
    @NotNull
    @EnumCurrencyType
    String currencyCode;

}
