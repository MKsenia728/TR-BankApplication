package com.example.bank_application.service.imp;

import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.dto.ManagerDto;
import com.example.bank_application.mapper.AccountMapper;
import com.example.bank_application.mapper.ManagerMapper;
import com.example.bank_application.repository.AccountRepository;
import com.example.bank_application.repository.ManagerRepository;
import com.example.bank_application.service.AccountService;
import com.example.bank_application.service.ManagerService;
import com.example.bank_application.service.exceptions.AccountNotFoundException;
import com.example.bank_application.service.exceptions.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ManagerServiceImp implements ManagerService {
    private final ManagerMapper managerMapper;
    private final ManagerRepository managerRepository;


    @Override
    public ManagerDto getManagerById(String id) {
        return managerMapper.toDto(managerRepository.findManagerById(Long.parseLong(id)).get());
    }

}

