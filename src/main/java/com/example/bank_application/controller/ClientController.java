package com.example.bank_application.controller;

import com.example.bank_application.dto.ClientWithBalanceDto;
import com.example.bank_application.service.interf.ClientService;
import com.example.bank_application.validation.annotation.EnumCurrencyType;
import com.example.bank_application.validation.annotation.PositiveDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    public final ClientService clientService;

    @GetMapping("/balance_more/{balance}/{currency}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientWithBalanceDto> getListClientsWithBalanceMoreThanInCurrency(@PositiveDecimal @PathVariable("balance") String balance, @EnumCurrencyType @PathVariable("currency") String currency) {
        return clientService.getListClientsWithBalanceMoreThan(balance, currency);
    }
}
