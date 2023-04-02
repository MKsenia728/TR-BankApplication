package com.example.bank_application.mapper;

import com.example.bank_application.dto.ManagerCreateDto;
import com.example.bank_application.entity.Manager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManagerCreateMapper {
    ManagerCreateDto toDto(Manager manager);
    Manager toEntity(ManagerCreateDto managerDto);
}
