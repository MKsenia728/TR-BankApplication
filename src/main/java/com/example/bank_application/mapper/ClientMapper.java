package com.example.bank_application.mapper;

import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.dto.ClientDto;
import com.example.bank_application.dto.ClientWithBalanceDto;
import com.example.bank_application.entity.Account;
import com.example.bank_application.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toDto(Client client);
//    @Mapping(source = "account.client.status", target="status")
//    @Mapping(source = "account.client.taxCode", target="taxCode")
//    @Mapping(source = "account.client.firstName", target="firstName")
//    @Mapping(source = "account.client.lastName", target="lastName")
//    @Mapping(source = "account.client.email", target="email")
//    @Mapping(source = "account.client.phone", target="phone")
//    @Mapping(source = "client.account.balance", target="balance")
    ClientWithBalanceDto toDtoWithBalance(Client client);

    Client toEntity(ClientDto clientDto);
    List<ClientWithBalanceDto> ToListDtoWithBalance(List<Client> clients);
}
