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
import com.example.bank_application.service.interf.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerServiceImpl implements ManagerService {
    private final ManagerMapper managerMapper;
    private final ManagerRepository managerRepository;

    @Override
    @Transactional
    public ManagerDto getManagerById(String id) {
        log.info("Get manager by id {}", id);
        return managerMapper.toDto(managerRepository.findManagerById(Long.parseLong(id)).orElseThrow(
                ()-> {
                    log.warn(ErrorMessage.MANAGER_NOT_FOUND);
                    throw new DataNotFoundException(ErrorMessage.MANAGER_NOT_FOUND);
                }));
    }
    @Override
    @Transactional
    public List<ManagerDto> getAllManagersWithClients() {
        log.info("Get all managers with clients");
        List<ManagerDto> resultList = new ArrayList<>(managerMapper.toListDto(managerRepository.getAllByClientsNotNull()));
        if (resultList.size() == 0) {
            log.warn(ErrorMessage.MANAGERS_NOT_FOUND);
            throw new DataNotFoundException(ErrorMessage.MANAGERS_NOT_FOUND);
        }
        return resultList;
    }

    @Override
    @Transactional
    public ManagerAfterCreateDto managerNewCreate(ManagerCreateDto managerCreateDto) {
        log.info("Create new manager");
        Manager manager = managerMapper.toCreateEntity(managerCreateDto);
        managerRepository.findAll().forEach(m -> {
            if (m.equals(manager)) {
                log.error(ErrorMessage.MANAGER_ALREADY_EXISTS);
                throw new DataAlreadyExistException(ErrorMessage.MANAGER_ALREADY_EXISTS);
            }
        });
        return  managerMapper.toAfterCreateDto(managerRepository.save(manager));
    }
}

