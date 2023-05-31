package com.example.bank_application.service.impl;

import com.example.bank_application.dto.ManagerAfterCreateDto;
import com.example.bank_application.dto.ManagerCreateDto;
import com.example.bank_application.dto.ManagerDto;
import com.example.bank_application.entity.Manager;
import com.example.bank_application.mapper.ManagerMapper;
import com.example.bank_application.repository.ManagerRepository;
import com.example.bank_application.service.exceptions.DataAlreadyExistException;
import com.example.bank_application.service.exceptions.DataNotFoundException;
import com.example.bank_application.service.exceptions.ErrorMessage;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Manager service test class")
@ExtendWith(MockitoExtension.class)
class ManagerServiceImpTest {

    @Mock
    ManagerRepository managerRepository;

    @Mock
    ManagerMapper managerMapper;

    @InjectMocks
    ManagerServiceImpl service;

    @DisplayName("Positive test. Get manager by Id")
    @Test
    void getManagerByIdTest() {
        Manager manager = EntityCreator.getManagerEntity();
        ManagerDto managerDto = DtoCreator.getManagerDto();

        Mockito.when(managerRepository.findManagerById(manager.getId())).thenReturn(Optional.of(manager));
        Mockito.when(managerMapper.toDto(manager)).thenReturn(managerDto);

        service.getManagerById(manager.getId().toString());

        Mockito.verify(managerRepository).findManagerById(manager.getId());
        Mockito.verify(managerMapper).toDto(manager);
    }

    @DisplayName("Negative test. Manager does not exist. Get manager by Id")
    @Test
    void getManagerByIdNotExistTest() {
        String id = "1";
        assertThrows(DataNotFoundException.class, () -> service.getManagerById(id));
    }

    @DisplayName("Positive test. Get all managers with clients")
    @Test
    void getAllManagersWithClientsTest() {
        List<Manager> managerList = new ArrayList<>();
        managerList.add(EntityCreator.getManagerEntity());
        List<ManagerDto> managerDtoList = new ArrayList<>();
        managerDtoList.add(DtoCreator.getManagerDto());

        Mockito.when(managerRepository.getAllByClientsNotNull()).thenReturn(managerList);
        Mockito.when(managerMapper.toListDto(managerList)).thenReturn(managerDtoList);

        service.getAllManagersWithClients();

        Mockito.verify(managerRepository).getAllByClientsNotNull();
        Mockito.verify(managerMapper).toListDto(managerList);
    }

    @DisplayName("Negative test. The managers with clients are absent. Get all managers with clients")
    @Test
    void getAllManagersWithClientsNotExistTest() {
        assertThrows(DataNotFoundException.class, () -> service.getAllManagersWithClients());
    }

    @DisplayName("Positive test. Create new manager")
    @Test
    void managerNewCreateTest() {
        Manager manager = EntityCreator.getManagerEntity();
        ManagerCreateDto managerCreateDto = DtoCreator.getManagerCreateDto();
        ManagerAfterCreateDto managerAfterCreateDto = DtoCreator.getManagerAfterCreateDto();
        List<Manager> managerList = new ArrayList<>();

        Mockito.when(managerRepository.findAll()).thenReturn(managerList);
        Mockito.when(managerMapper.toCreateEntity(managerCreateDto)).thenReturn(manager);
        Mockito.when(managerMapper.toAfterCreateDto(manager)).thenReturn(managerAfterCreateDto);
        Mockito.when(managerRepository.save(manager)).thenReturn(manager);

        service.managerNewCreate(managerCreateDto);

        Mockito.verify(managerRepository).findAll();
        Mockito.verify(managerMapper).toCreateEntity(managerCreateDto);
        Mockito.verify(managerMapper).toAfterCreateDto(manager);
        Mockito.verify(managerRepository).save(manager);
    }

    @DisplayName("Negative test. Such manager already exist. Create new manager")
    @Test
    void managerNewCreateAlreadyExistTest() {
        ManagerCreateDto managerCreateDto = DtoCreator.getManagerCreateDto();

        Mockito.when(managerRepository.findAll()).thenThrow(new DataAlreadyExistException(ErrorMessage.MANAGER_ALREADY_EXISTS));
        assertThrows(DataAlreadyExistException.class, () -> service.managerNewCreate(managerCreateDto));
    }
}