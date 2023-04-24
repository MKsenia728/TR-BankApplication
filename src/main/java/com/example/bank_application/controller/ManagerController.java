package com.example.bank_application.controller;

import com.example.bank_application.dto.managerDto.ManagerAfterCreateDto;
import com.example.bank_application.dto.managerDto.ManagerCreateDto;
import com.example.bank_application.dto.managerDto.ManagerDto;
import com.example.bank_application.dto.managerDto.ManagerListDto;
import com.example.bank_application.service.interf.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/managers")
@RequiredArgsConstructor
public class ManagerController {
    public final ManagerService managerService;

    @GetMapping(value = "/id/{managerId}")
    @ResponseStatus(HttpStatus.OK)
    public ManagerDto getManagerById(@PathVariable("managerId") String managerId) {
        return managerService.getManagerById(managerId);
    }

    @GetMapping(value="/all/withClients")
    @ResponseStatus(HttpStatus.OK)
    public ManagerListDto getAllManagers() {
        return managerService.getAllManagersWithClients();
    }

    @PostMapping( value = "/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ManagerAfterCreateDto createNewManager(@RequestBody ManagerCreateDto managerCreateDto) {
        return managerService.managerNewCreate(managerCreateDto);
    }

}
