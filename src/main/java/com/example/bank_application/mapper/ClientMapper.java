package com.example.bank_application.mapper;

import com.example.bank_application.dto.ClientDto;
import com.example.bank_application.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toDto(Client client);

    Client toEntity(ClientDto clientDto);
}
