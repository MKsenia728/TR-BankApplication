package com.example.bank_application.mapper;

import com.example.bank_application.dto.ManagerDto;
import com.example.bank_application.entity.Manager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagerDto toDto(Manager manager);
    Manager toEntity(ManagerDto managerDto);
}
