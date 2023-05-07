package com.example.bank_application.mapper.impl;

import com.example.bank_application.dto.managerDto.ManagerAfterCreateDto;
import com.example.bank_application.dto.managerDto.ManagerCreateDto;
import com.example.bank_application.dto.managerDto.ManagerDto;
import com.example.bank_application.entity.Manager;
import com.example.bank_application.entity.enums.ManagerStatus;
import com.example.bank_application.mapper.ManagerMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ManagerMapperImpl implements ManagerMapper {
    public ManagerDto toDto(Manager manager) {
        if (manager == null) {
            return null;
        } else {
            String id = null;
            String firstName;
            String lastName;
            String status = null;
            LocalDateTime createdAt;
            LocalDateTime updatedAt;
            if (manager.getId() != null) {
                id = String.valueOf(manager.getId());
            }

            firstName = manager.getFirstName();
            lastName = manager.getLastName();
            if (manager.getStatus() != null) {
                status = manager.getStatus().name();
            }

            createdAt = manager.getCreatedAt();
            updatedAt = manager.getUpdatedAt();
            return new ManagerDto(id, firstName, lastName, status, createdAt, updatedAt);
        }
    }

    public List<ManagerDto> toListDto(List<Manager> managers) {
        if (managers == null) {
            return null;
        } else {
            List<ManagerDto> list = new ArrayList<>(managers.size());

            for (Manager manager : managers) {
                list.add(this.toDto(manager));
            }

            return list;
        }
    }

    public Manager toCreateEntity(ManagerCreateDto managerDto) {
        if (managerDto == null) {
            return null;
        } else {
            Manager manager = new Manager();
            manager.setFirstName(managerDto.getFirstName());
            manager.setLastName(managerDto.getLastName());
            manager.setCreatedAt(LocalDateTime.now());
            manager.setUpdatedAt(LocalDateTime.now());
            manager.setStatus(ManagerStatus.PENDING);
            return manager;
        }
    }

    public ManagerAfterCreateDto toAfterCreateDto(Manager manager) {
        if (manager == null) {
            return null;
        } else {
            String id = null;
            String firstName;
            String lastName;
            String status = null;
            LocalDateTime createdAt;
            LocalDateTime updatedAt;
            if (manager.getId() != null) {
                id = String.valueOf(manager.getId());
            }

            firstName = manager.getFirstName();
            lastName = manager.getLastName();
            if (manager.getStatus() != null) {
                status = manager.getStatus().name();
            }

            createdAt = manager.getCreatedAt();
            updatedAt = manager.getUpdatedAt();
            return new ManagerAfterCreateDto(id, firstName, lastName, status, createdAt, updatedAt);
        }
    }
}
