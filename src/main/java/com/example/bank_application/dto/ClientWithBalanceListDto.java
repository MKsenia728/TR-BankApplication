package com.example.bank_application.dto;

import com.example.bank_application.dto.ClientWithBalanceDto;
import lombok.Value;

import java.util.List;
@Value
public class ClientWithBalanceListDto {
    List<ClientWithBalanceDto> clientWithBalanceDtoList;
}
