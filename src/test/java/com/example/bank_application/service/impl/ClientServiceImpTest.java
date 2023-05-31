package com.example.bank_application.service.impl;

import com.example.bank_application.dto.ClientWithBalanceDto;
import com.example.bank_application.entity.Client;
import com.example.bank_application.entity.enums.CurrencyType;
import com.example.bank_application.mapper.ClientMapper;
import com.example.bank_application.repository.ClientRepository;
import com.example.bank_application.service.exceptions.DataNotFoundException;
import com.example.bank_application.util.DtoCreator;
import com.example.bank_application.util.EntityCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;

@DisplayName("Client service test class")
@ExtendWith(MockitoExtension.class)
class ClientServiceImpTest {

    @Mock
    ClientRepository clientRepository;

    @Mock
    ClientMapper clientMapper;

    @InjectMocks
    ClientServiceImpl service;

    private String balance;
    private String currency;

    private Client client;

    List<Client> clientList;

    @BeforeEach
    void init() {
        balance = "5000";
        currency = "USD";
        client = EntityCreator.getClientEntity();
        clientList = new ArrayList<>();
    }

    @Test
    @DisplayName("Positive test. Get list of clients with balance more then given")
    void getListClientsWithBalanceMoreThanTest() {
        clientList.add(client);

        List<ClientWithBalanceDto> resClientListDto = new ArrayList<>();
        resClientListDto.add(DtoCreator.getClientWithBalanceDto());

        Mockito.when(clientRepository.findByAccounts_BalanceGreaterThanEqualAndAccounts_CurrencyCode(Double.parseDouble(balance), CurrencyType.valueOf(currency))).thenReturn(clientList);
        Mockito.when(clientMapper.toListDtoWithBalance(clientList)).thenReturn(resClientListDto);

        service.getListClientsWithBalanceMoreThan(balance, currency);

        Mockito.verify(clientRepository).findByAccounts_BalanceGreaterThanEqualAndAccounts_CurrencyCode(Double.parseDouble(balance), CurrencyType.valueOf(currency));
        Mockito.verify(clientMapper).toListDtoWithBalance(clientList);
    }

    @Test
    @DisplayName("Negative test. Data does not found")
    void getDataNotFoundExceptionListClientsWithBalanceMoreThanTest() {
        Mockito.when(clientRepository.findByAccounts_BalanceGreaterThanEqualAndAccounts_CurrencyCode(Double.parseDouble(balance), CurrencyType.valueOf(currency))).thenReturn(clientList);
        assertThrows(DataNotFoundException.class, () -> service.getListClientsWithBalanceMoreThan(balance, currency));
    }
}