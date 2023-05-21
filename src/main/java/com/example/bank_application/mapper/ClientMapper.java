package com.example.bank_application.mapper;

import com.example.bank_application.dto.ClientDto;
import com.example.bank_application.dto.ClientWithBalanceDto;
import com.example.bank_application.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AccountMapper.class})
public interface ClientMapper {
    ClientDto toDto(Client client);

    @Mapping(source = "client.accounts", target="balanceAndCurrency")
    ClientWithBalanceDto toDtoWithBalance(Client client);

    Client toEntity(ClientDto clientDto);

    List<ClientWithBalanceDto> toListDtoWithBalance(List<Client> clients);
}
