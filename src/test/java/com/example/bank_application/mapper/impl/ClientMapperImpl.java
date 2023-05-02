package com.example.bank_application.mapper.impl;

import com.example.bank_application.dto.clientDto.ClientDto;
import com.example.bank_application.dto.clientDto.ClientWithBalanceDto;
import com.example.bank_application.entity.Client;
import com.example.bank_application.entity.enums.ClientStatus;
import com.example.bank_application.mapper.ClientMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Component
public class ClientMapperImpl implements ClientMapper {
    public ClientDto toDto(Client client) {
        if (client == null) {
            return null;
        } else {
            String id = null;
            String status = null;
            String taxCode = null;
            String firstName = null;
            String lastName = null;
            String email = null;
            String address = null;
            String phone = null;
            LocalDateTime createdAt = null;
            LocalDateTime updatedAt = null;
            if (client.getId() != null) {
                id = client.getId().toString();
            }

            if (client.getStatus() != null) {
                status = client.getStatus().name();
            }

            taxCode = client.getTaxCode();
            firstName = client.getFirstName();
            lastName = client.getLastName();
            email = client.getEmail();
            address = client.getAddress();
            phone = client.getPhone();
            createdAt = client.getCreatedAt();
            updatedAt = client.getUpdatedAt();
            ClientDto clientDto = new ClientDto(id, status, taxCode, firstName, lastName, email, address, phone, createdAt, updatedAt);
            return clientDto;
        }
    }

    public ClientWithBalanceDto toDtoWithBalance(Client client) {
        if (client == null) {
            return null;
        } else {
            String status = null;
            String taxCode = null;
            String firstName = null;
            String lastName = null;
            String email = null;
            String phone = null;
            if (client.getStatus() != null) {
                status = client.getStatus().name();
            }

            taxCode = client.getTaxCode();
            firstName = client.getFirstName();
            lastName = client.getLastName();
            email = client.getEmail();
            phone = client.getPhone();
            ClientWithBalanceDto clientWithBalanceDto = new ClientWithBalanceDto(status, taxCode, firstName, lastName, email, phone);
            return clientWithBalanceDto;
        }
    }

    public Client toEntity(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        } else {
            Client client = new Client();
            if (clientDto.getId() != null) {
                client.setId(UUID.fromString(clientDto.getId()));
            }

            if (clientDto.getStatus() != null) {
                client.setStatus((ClientStatus)Enum.valueOf(ClientStatus.class, clientDto.getStatus()));
            }

            client.setTaxCode(clientDto.getTaxCode());
            client.setFirstName(clientDto.getFirstName());
            client.setLastName(clientDto.getLastName());
            client.setEmail(clientDto.getEmail());
            client.setAddress(clientDto.getAddress());
            client.setPhone(clientDto.getPhone());
            client.setCreatedAt(clientDto.getCreatedAt());
            client.setUpdatedAt(clientDto.getUpdatedAt());
            return client;
        }
    }

    public List<ClientWithBalanceDto> toListDtoWithBalance(List<Client> clients) {
        if (clients == null) {
            return null;
        } else {
            List<ClientWithBalanceDto> list = new ArrayList(clients.size());
            Iterator var3 = clients.iterator();

            while(var3.hasNext()) {
                Client client = (Client)var3.next();
                list.add(this.toDtoWithBalance(client));
            }

            return list;
        }
    }
}
