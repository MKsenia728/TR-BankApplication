package com.example.bank_application.controller;

import com.example.bank_application.dto.ClientWithBalanceDto;
import com.example.bank_application.service.interf.ClientService;
import com.example.bank_application.util.DtoCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClientController.class)
@DisplayName("ClientController test class")
class ClientControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ClientService service;

    @Autowired
    MockMvc mockMvc;

    @DisplayName("Positive test. Status 200, JSON response. Controller for find account by balance")
    @Test
    void getListClientsWithBalanceMoreThanTest() throws Exception {
        String balance = "11000";
        String currency = "EUR";
        List<ClientWithBalanceDto> clientsDto = new ArrayList<>();
        clientsDto.add(DtoCreator.getClientWithBalanceDto());

        Mockito.when(service.getListClientsWithBalanceMoreThan(balance, currency)).thenReturn(clientsDto);

        mockMvc.perform(
                        get("/clients/balance_more/" + balance + "/" + currency)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lastName").value(clientsDto.get(0).getLastName()));
        Mockito.verify(service).getListClientsWithBalanceMoreThan(balance, currency);


    }
}