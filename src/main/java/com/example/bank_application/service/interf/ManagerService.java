package com.example.bank_application.service.interf;

import com.example.bank_application.dto.managerDto.ManagerAfterCreateDto;
import com.example.bank_application.dto.managerDto.ManagerCreateDto;
import com.example.bank_application.dto.managerDto.ManagerDto;
import com.example.bank_application.dto.managerDto.ManagerListDto;

public interface ManagerService {
    ManagerDto getManagerById(String id);
    ManagerListDto getAllManagersWithClients();

    ManagerAfterCreateDto managerNewCreate(ManagerCreateDto managerCreateDto);
}
