package com.example.bank_application.controller;

import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.dto.AccountsListDto;
import com.example.bank_application.dto.ManagerDto;
import com.example.bank_application.service.AccountService;
import com.example.bank_application.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerController {
    public final ManagerService managerService;

    @GetMapping("/id/{managerId}")
    @ResponseStatus(HttpStatus.OK)
    public ManagerDto getManagerById(@PathVariable("managerId") String managerId) {
        return managerService.getManagerById(managerId);
    }
}
