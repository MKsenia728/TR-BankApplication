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
import java.util.Iterator;
import java.util.List;

@Component
public class ManagerMapperImpl implements ManagerMapper {
    public ManagerDto toDto(Manager manager) {
        if (manager == null) {
            return null;
        } else {
            String id = null;
            String firstName = null;
            String lastName = null;
            String status = null;
            LocalDateTime createdAt = null;
            LocalDateTime updatedAt = null;
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
            ManagerDto managerDto = new ManagerDto(id, firstName, lastName, status, createdAt, updatedAt);
            return managerDto;
        }
    }

    public Manager toCreateEntity(ManagerDto managerDto) {
        if (managerDto == null) {
            return null;
        } else {
            Manager manager = new Manager();
            if (managerDto.getId() != null) {
                manager.setId(Long.parseLong(managerDto.getId()));
            }

            manager.setFirstName(managerDto.getFirstName());
            manager.setLastName(managerDto.getLastName());
            if (managerDto.getStatus() != null) {
                manager.setStatus((ManagerStatus)Enum.valueOf(ManagerStatus.class, managerDto.getStatus()));
            }

            manager.setCreatedAt(managerDto.getCreatedAt());
            manager.setUpdatedAt(managerDto.getUpdatedAt());
            return manager;
        }
    }

    public List<ManagerDto> toListDto(List<Manager> managers) {
        if (managers == null) {
            return null;
        } else {
            List<ManagerDto> list = new ArrayList(managers.size());
            Iterator var3 = managers.iterator();

            while(var3.hasNext()) {
                Manager manager = (Manager)var3.next();
                list.add(this.toDto(manager));
            }

            return list;
        }
    }

    public ManagerCreateDto toCreateDto(Manager manager) {
        if (manager == null) {
            return null;
        } else {
            String firstName = null;
            String lastName = null;
            firstName = manager.getFirstName();
            lastName = manager.getLastName();
            ManagerCreateDto managerCreateDto = new ManagerCreateDto(firstName, lastName);
            return managerCreateDto;
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
            String firstName = null;
            String lastName = null;
            String status = null;
            LocalDateTime createdAt = null;
            LocalDateTime updatedAt = null;
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
            ManagerAfterCreateDto managerAfterCreateDto = new ManagerAfterCreateDto(id, firstName, lastName, status, createdAt, updatedAt);
            return managerAfterCreateDto;
        }
    }
}
