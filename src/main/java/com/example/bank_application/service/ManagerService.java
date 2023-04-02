package com.example.bank_application.service;

import com.example.bank_application.dto.ManagerCreateDto;
import com.example.bank_application.dto.ManagerDto;
import com.example.bank_application.dto.ManagerListDto;

public interface ManagerService {
    ManagerDto getManagerById(String id);
    ManagerListDto getAllManagersWithClients();

    void managerNewCreate(ManagerCreateDto managerCreateDto);
}
