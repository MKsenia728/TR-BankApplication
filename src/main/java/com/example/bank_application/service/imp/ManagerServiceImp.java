package com.example.bank_application.service.imp;

import com.example.bank_application.dto.ManagerCreateDto;
import com.example.bank_application.dto.ManagerDto;
import com.example.bank_application.dto.ManagerListDto;
import com.example.bank_application.mapper.ManagerCreateMapper;
import com.example.bank_application.mapper.ManagerMapper;
import com.example.bank_application.repository.ManagerRepository;
import com.example.bank_application.service.ManagerService;
import com.example.bank_application.service.exceptions.ErrorMessage;
import com.example.bank_application.service.exceptions.ManagerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ManagerServiceImp implements ManagerService {
    private final ManagerMapper managerMapper;
    private final ManagerCreateMapper managerCreateMapper;
    private final ManagerRepository managerRepository;

    @Override
    public ManagerDto getManagerById(String id) {
        return managerMapper.toDto(managerRepository.findManagerById(Long.parseLong(id)).orElseThrow(
                ()-> new ManagerNotFoundException(ErrorMessage.MANAGER_NOT_FOUND)));
    }
    @Override
    public ManagerListDto getAllManagersWithClients() {
        return new ManagerListDto(managerMapper.toListDto(managerRepository.getAllByClientsNotNull()));
    }

    @Override
    public void managerNewCreate(ManagerCreateDto managerCreateDto) {
        managerRepository.save(managerCreateMapper.toEntity(managerCreateDto));
    }

}

