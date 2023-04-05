package com.example.bank_application.dto;

import lombok.Value;

import java.util.List;
@Value
public class ClientWithBalanceListDto {
    List<ClientWithBalanceDto> clientWithBalanceDtoList;
}
