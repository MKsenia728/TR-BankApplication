package com.example.bank_application.mapper;

import com.example.bank_application.dto.managerDto.ManagerAfterCreateDto;
import com.example.bank_application.dto.managerDto.ManagerCreateDto;
import com.example.bank_application.dto.managerDto.ManagerDto;
import com.example.bank_application.entity.Manager;
import com.example.bank_application.entity.enums.ManagerStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR, imports = {LocalDateTime.class, ManagerStatus.class})
public interface ManagerMapper {
    ManagerDto toDto(Manager manager);

    List<ManagerDto> toListDto(List<Manager> managers);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "status", expression = "java(ManagerStatus.PENDING)")
    Manager toCreateEntity(ManagerCreateDto managerDto);

    ManagerAfterCreateDto toAfterCreateDto(Manager manager);
}
