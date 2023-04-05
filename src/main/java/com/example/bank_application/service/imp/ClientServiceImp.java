package com.example.bank_application.service.imp;

import com.example.bank_application.dto.ClientWithBalanceListDto;
import com.example.bank_application.mapper.ClientMapper;
import com.example.bank_application.repository.ClientRepository;
import com.example.bank_application.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ClientServiceImp implements ClientService {
    final private ClientRepository clientRepository;

    final private ClientMapper clientMapper;


    @Override
    public ClientWithBalanceListDto getListClientsWithBalanceMoreThan(String balance) {
       return new ClientWithBalanceListDto(clientMapper.ToListDtoWithBalance(clientRepository.findClientsBy(Double.parseDouble(balance))));
    }
}
