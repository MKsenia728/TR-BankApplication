package com.example.bank_application.service;

import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.dto.ClientWithBalanceDto;
import com.example.bank_application.dto.ClientWithBalanceListDto;

import java.util.List;

public interface ClientService {

    ClientWithBalanceListDto getListClientsWithBalanceMoreThan(String balance);
}
