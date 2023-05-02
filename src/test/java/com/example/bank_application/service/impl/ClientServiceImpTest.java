package com.example.bank_application.service.impl;

import com.example.bank_application.dto.clientDto.ClientWithBalanceDto;
import com.example.bank_application.entity.Client;
import com.example.bank_application.mapper.ClientMapper;
import com.example.bank_application.repository.ClientRepository;
import com.example.bank_application.service.exceptions.DataNotFoundException;
import com.example.bank_application.util.DtoCreator;
import com.example.bank_application.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Client service test class")
@ExtendWith(MockitoExtension.class)
class ClientServiceImpTest {
    @Mock
    ClientRepository clientRepository;

    @Mock
    ClientMapper clientMapper;

    @InjectMocks
    ClientServiceImp service;

    @Test
    @DisplayName("Positive test. Get list of clients with balance more then given")
    void getListClientsWithBalanceMoreThanTest() {
        String balance = "5000";
        Client client = EntityCreator.getClientEntity();
        List<Client> clientList = new ArrayList<>();
        clientList.add(client);
        List<ClientWithBalanceDto> resClientListDto = new ArrayList<>();
        resClientListDto.add(DtoCreator.getClientWithBalanceDto());

        Mockito.when(clientRepository.findClientsBy(Double.parseDouble(balance))).thenReturn(clientList);
        Mockito.when(clientMapper.toListDtoWithBalance(clientList)).thenReturn(resClientListDto);

        service.getListClientsWithBalanceMoreThan(balance);
        Mockito.verify(clientRepository).findClientsBy(Double.parseDouble(balance));
        Mockito.verify(clientMapper).toListDtoWithBalance(clientList);
    }

    @Test
    @DisplayName("Negative test. Argument balance is incorrect")
    void getIllegalArgumentExceptionListClientsWithBalanceMoreThanTest() {
        String balance = "many";
        assertThrows(IllegalArgumentException.class, () -> service.getListClientsWithBalanceMoreThan(balance));
    }

    @Test
    @DisplayName("Negative test. Data is missing")
    void getDataNotFoundExceptionListClientsWithBalanceMoreThanTest() {
        String balance = "5000";
        List<Client> clientList = new ArrayList<>();
        Mockito.when(clientRepository.findClientsBy(Double.valueOf(balance))).thenReturn(clientList);
        assertThrows(DataNotFoundException.class, () -> service.getListClientsWithBalanceMoreThan(balance));
    }
}