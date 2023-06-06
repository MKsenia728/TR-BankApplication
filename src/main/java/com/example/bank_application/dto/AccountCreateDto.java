package com.example.bank_application.dto;

import com.example.bank_application.validation.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
@Schema(description = "Info for create new account")
public class AccountCreateDto {
    @NotNull
    @Iban
    @Schema(description = "Account`s name should have IBAN format")
    String name;

    @EnumAccountTypeOrNull
    @Schema(description = "Type should be CURRENT / DEPOSIT / CREDIT, if you stay it empty, value will be CURRENT ")
    String type;

    @EnumAccountStatusOrNull
    @Schema(description = "Status should be PENDING / ACTIVE / BLOCKED, if you stay it empty, value will be PENDING ")
    String status;

    @PositiveDecimalOrNull
    @Schema(description = "Status should be 0.0 or more, if you stay it empty, value will be 0.0 ")
    String balance;
    @NotNull
    @EnumCurrencyType
    @Schema(description = "Currency type for new account ")
    String currencyCode;

}
