package com.example.bank_application.mapper;

import com.example.bank_application.dto.managerDto.ManagerCreateDto;
import com.example.bank_application.entity.Manager;
import com.example.bank_application.entity.enums.ManagerStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR, imports = {LocalDateTime.class, ManagerStatus.class})
public interface ManagerCreateMapper {
    ManagerCreateDto toDto(Manager manager);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "status", expression = "java(ManagerStatus.PENDING)")
    Manager toEntity(ManagerCreateDto managerDto);
}
