package com.example.bank_application.service.util;

import com.example.bank_application.dto.clientDto.ClientWithBalanceListDto;

public interface ClientService {

    ClientWithBalanceListDto getListClientsWithBalanceMoreThan(String balance);
}
