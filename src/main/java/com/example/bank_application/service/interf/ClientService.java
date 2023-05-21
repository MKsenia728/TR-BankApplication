package com.example.bank_application.service.interf;

import com.example.bank_application.dto.ClientWithBalanceDto;

import java.util.List;

public interface ClientService {

    List<ClientWithBalanceDto> getListClientsWithBalanceMoreThan(String balance, String currency);
}
