package com.example.bank_application.service.impl;

import com.example.bank_application.dto.ClientWithBalanceListDto;
import com.example.bank_application.entity.Client;
import com.example.bank_application.mapper.ClientMapper;
import com.example.bank_application.repository.ClientRepository;
import com.example.bank_application.service.exceptions.DataNotFoundException;
import com.example.bank_application.service.exceptions.ErrorMessage;
import com.example.bank_application.service.interf.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {
    final private ClientRepository clientRepository;

    final private ClientMapper clientMapper;


    @Override
    public ClientWithBalanceListDto getListClientsWithBalanceMoreThan(String balance) {
        log.info("Get list of client with balance more than {}", balance);
        double balanceD;
        balanceD = Double.parseDouble(balance);
        List<Client> clientList = clientRepository.findClientsBy(balanceD);
        if (clientList.size() == 0) {
            log.warn(ErrorMessage.CLIENTS_NOT_FOUND);
            throw new DataNotFoundException(ErrorMessage.CLIENTS_NOT_FOUND);
        }
        return new ClientWithBalanceListDto(clientMapper.toListDtoWithBalance(clientList));

    }
}
