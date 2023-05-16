package com.example.bank_application.service.interf;

import com.example.bank_application.dto.ManagerAfterCreateDto;
import com.example.bank_application.dto.ManagerCreateDto;
import com.example.bank_application.dto.ManagerDto;
import com.example.bank_application.dto.ManagerListDto;

import java.util.List;

public interface ManagerService {
    ManagerDto getManagerById(String id);
    List<ManagerDto> getAllManagersWithClients();

    ManagerAfterCreateDto managerNewCreate(ManagerCreateDto managerCreateDto);
}
