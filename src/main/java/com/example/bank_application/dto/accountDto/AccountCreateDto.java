package com.example.bank_application.dto.accountDto;

import com.example.bank_application.validation.annotation.EnumAccountStatus;
import com.example.bank_application.validation.annotation.EnumAccountType;
import com.example.bank_application.validation.annotation.EnumCurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.springframework.lang.NonNull;

@Value
public class AccountCreateDto {
    @NotNull
    String name;
    @EnumAccountType
    String type;
    @EnumAccountStatus
    String status;

    String balance;
    @NotNull
    @EnumCurrencyType
    String currencyCode;

}
