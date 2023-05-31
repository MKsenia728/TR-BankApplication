package com.example.bank_application.controller;

import com.example.bank_application.dto.*;
import com.example.bank_application.service.interf.ManagerService;
import com.example.bank_application.validation.annotation.PositiveInteger;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
@RequestMapping(value = "/managers")
@RequiredArgsConstructor
public class ManagerController {
    public final ManagerService managerService;

    @GetMapping(value = "/id/{managerId}")
    @ResponseStatus(HttpStatus.OK)
    public ManagerDto getManagerById(@PositiveInteger @PathVariable("managerId") String managerId) {
        return managerService.getManagerById(managerId);
    }

    @GetMapping(value="/all/withClients")
    @ResponseStatus(HttpStatus.OK)
    public List<ManagerDto> getAllManagersWithClients() {
        return managerService.getAllManagersWithClients();
    }

    @PostMapping( value = "/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ManagerAfterCreateDto createNewManager(@Valid @RequestBody ManagerCreateDto managerCreateDto) {
        return managerService.managerNewCreate(managerCreateDto);
    }

}
