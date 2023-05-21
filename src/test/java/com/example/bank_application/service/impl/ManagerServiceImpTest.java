package com.example.bank_application.service.impl;

import com.example.bank_application.mapper.ClientMapper;
import com.example.bank_application.mapper.ManagerMapper;
import com.example.bank_application.repository.ClientRepository;
import com.example.bank_application.repository.ManagerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Manager service test class")
@ExtendWith(MockitoExtension.class)
class ManagerServiceImpTest {
    @Mock
    ManagerRepository managerRepository;

    @Mock
    ManagerMapper managerMapper;

    @InjectMocks
    ManagerServiceImpl service;
    @Test
    void getManagerById() {
    }

    @Test
    void getAllManagersWithClients() {
    }

    @Test
    void managerNewCreate() {
    }
}