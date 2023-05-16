package com.example.bank_application.controller;

import com.example.bank_application.dto.ManagerAfterCreateDto;
import com.example.bank_application.dto.ManagerCreateDto;
import com.example.bank_application.dto.ManagerDto;
import com.example.bank_application.dto.ManagerListDto;
import com.example.bank_application.service.interf.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<ManagerDto> getAllManagers() {
        return managerService.getAllManagersWithClients();
    }

    @PostMapping( value = "/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ManagerAfterCreateDto createNewManager(@RequestBody ManagerCreateDto managerCreateDto) {
        return managerService.managerNewCreate(managerCreateDto);
    }

}
