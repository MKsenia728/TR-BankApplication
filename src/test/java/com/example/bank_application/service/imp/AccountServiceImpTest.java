package com.example.bank_application.service.imp;

import com.example.bank_application.dto.accountDto.*;
import com.example.bank_application.entity.Account;
import com.example.bank_application.entity.Client;
import com.example.bank_application.entity.enums.AccountStatus;
import com.example.bank_application.mapper.AccountMapper;
import com.example.bank_application.repository.AccountRepository;
import com.example.bank_application.repository.ClientRepository;
import com.example.bank_application.service.exceptions.*;
import com.example.bank_application.service.util.DtoCreator;
import com.example.bank_application.service.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("Account service test class")
@ExtendWith(MockitoExtension.class)
class AccountServiceImpTest {
    @InjectMocks
    AccountServiceImp service;
    @Mock
    AccountMapper accountMapper;
    @Mock
    AccountRepository accountRepository;
    @Mock
    ClientRepository clientRepository;

    @Test
    @DisplayName("Positive test. Get account by Id")
    void getAccountByIdTest() {
        Account account = EntityCreator.getAccountEntity();
        AccountDto accountDto = DtoCreator.getAccountDto();

        Mockito.when(accountRepository.findAccountById(account.getId())).thenReturn(Optional.ofNullable(account));
        Mockito.when(accountMapper.toDto(account)).thenReturn(accountDto);

        AccountDto resultAccountDto = service.getAccountById(account.getId().toString());
        assertEquals(resultAccountDto, accountDto);
        Mockito.verify(accountRepository).findAccountById(account.getId());
        Mockito.verify(accountMapper).toDto(account);
    }

    @Test
    @DisplayName("Negative test. Get account by Id")
    void getNotExistAccountByIdTest() {
        Account account = EntityCreator.getAccountEntity();
        assertThrows(AccountNotFoundException.class, () -> service.getAccountById(account.getId().toString()));
    }

    @Test
    void getAllAccountsTest() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(EntityCreator.getAccountEntity());
        List<AccountDto> accountDtoList = new ArrayList<>();
        accountDtoList.add(DtoCreator.getAccountDto());

        when(accountRepository.getAllBy()).thenReturn(accountList);
        when(accountMapper.ToListDto(accountList)).thenReturn(accountDtoList);

        AccountsListDto expectAccountListDto = new AccountsListDto(accountDtoList);
        AccountsListDto resultAccountListDto = service.getAllAccounts();
        assertEquals(expectAccountListDto, resultAccountListDto);
    }

    @Test
    void getNotExistAllAccountsTest() {
        when(accountRepository.getAllBy()).thenReturn(null);
        assertThrows(AccountNotFoundException.class, () -> service.getAllAccounts());
    }

    @Test
    void getAllAccountsByStatusTest() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(EntityCreator.getAccountEntity());
        List<AccountDto> accountDtoList = new ArrayList<>();
        accountDtoList.add(DtoCreator.getAccountDto());
        String status = "ACTIVE";

        when(accountRepository.getAllByStatus(AccountStatus.valueOf(status))).thenReturn(accountList);
        when(accountMapper.ToListDto(accountList)).thenReturn(accountDtoList);

        AccountsListDto expectAccountListDto = new AccountsListDto(accountDtoList);
        AccountsListDto resultAccountListDto = service.getAllAccountsByStatus(status);
        assertEquals(expectAccountListDto, resultAccountListDto);

    }

    @Test
    void getNotExistAllAccountsByStatusTest() {
        String status = "PENDING";
        when(accountRepository.getAllByStatus(AccountStatus.valueOf(status))).thenReturn(null);
        assertThrows(AccountNotFoundException.class, () -> service.getAllAccountsByStatus(status));
    }

    @Test
    void getInvalidStatusExceptionAllAccountsByStatusTest() {
        String status = "Something wrong";
        assertThrows(InvalidSearchArgument.class, () -> service.getAllAccountsByStatus(status));
    }

    @Test
    void createNewAccountTest() {
        Client client = EntityCreator.getClientEntity();
        String taxCode = client.getTaxCode();
        Mockito.when(clientRepository.findClientByTaxCode(taxCode)).thenReturn(client);

        AccountCreateDto accountCreateDto = DtoCreator.getAccountCreateDto();
        Account account = EntityCreator.getAccountEntity();
        Mockito.when(accountRepository.findAccountByName(accountCreateDto.getName())).thenReturn(null);
        Mockito.when(accountMapper.toEntity(accountCreateDto)).thenReturn(account);

        AccountAfterCreateDto expectAccountAfterCreateDto = DtoCreator.getAccountAfterCreateDto("ACTIVE");
        Mockito.when(accountMapper.toDtoAfterCreate(account)).thenReturn(expectAccountAfterCreateDto);
        Mockito.when(accountRepository.save(account)).thenReturn(account);

        AccountAfterCreateDto resultAccountAfterCreateDto = service.createNewAccount(accountCreateDto, taxCode);

        assertEquals(expectAccountAfterCreateDto,resultAccountAfterCreateDto);
        Mockito.verify(clientRepository).findClientByTaxCode(taxCode);
        Mockito.verify(accountRepository).findAccountByName(accountCreateDto.getName());
        Mockito.verify(accountMapper).toEntity(accountCreateDto);
        Mockito.verify(accountMapper).toDtoAfterCreate(account);
        Mockito.verify(accountRepository).save(account);
    }
    @Test
    void createNotFoundClientExceptionNewAccountTest() {
        String taxCode = "111";
        Mockito.when(clientRepository.findClientByTaxCode(taxCode)).thenReturn(null);
        AccountCreateDto accountCreateDto = DtoCreator.getAccountCreateDto();
        assertThrows(ClientNotFoundByTaxCodeException.class, () -> service.createNewAccount(accountCreateDto, taxCode));
    }
    @Test
    void createAlreadyExistAccountExceptionNewAccountTest() {
        Client client = EntityCreator.getClientEntity();
        String taxCode = client.getTaxCode();
        Mockito.when(clientRepository.findClientByTaxCode(taxCode)).thenReturn(client);

        AccountCreateDto accountCreateDto = DtoCreator.getAccountCreateDto();
        Account account = EntityCreator.getAccountEntity();
        Mockito.when(accountRepository.findAccountByName(accountCreateDto.getName())).thenReturn(account);

        assertThrows(AccountAlreadyExistException.class, () -> service.createNewAccount(accountCreateDto, taxCode));

    }
    @Test
    void createInvalidArgumentExceptionNewAccountTest() {
        Client client = EntityCreator.getClientEntity();
        String taxCode = client.getTaxCode();
        Mockito.when(clientRepository.findClientByTaxCode(taxCode)).thenReturn(client);

        AccountCreateDto accountCreateDto = DtoCreator.getAccountCreateDto();
        Account account = new Account();
        Mockito.when(accountRepository.findAccountByName(accountCreateDto.getName())).thenReturn(null);
        Mockito.when(accountMapper.toEntity(accountCreateDto)).thenReturn(account);
        assertThrows(NotEnoughDataToCreateEntity.class, () -> service.createNewAccount(accountCreateDto, taxCode));
    }

    @Test
    void blockAccountByProductIdAndStatusTest() {
        String productId = "1";
        String status = "ACTIVE";
        List<Account> accountList = new ArrayList<>();
        accountList.add(EntityCreator.getAccountEntity());
        Mockito.when(accountRepository.getAllByStatus(AccountStatus.valueOf(status))).thenReturn(accountList);

        accountList.get(0).setStatus(AccountStatus.BLOCKED);
        List<Account> updatedList = new ArrayList<>(accountList);
        Mockito.when(accountRepository.saveAll(updatedList)).thenReturn(updatedList);
        List<AccountAfterCreateDto> accountAfterCreateDtoList = new ArrayList<>();
        accountAfterCreateDtoList.add(DtoCreator.getAccountAfterCreateDto("BLOCKED"));
        Mockito.when(accountMapper.toListAfterCreateDto(accountList)).thenReturn(accountAfterCreateDtoList);
        AccountsListAfterCreateDto resultList = service.blockAccountByProductIdAndStatus(productId, status);
        assertEquals(accountAfterCreateDtoList.get(0), resultList.getAccountAfterCreateDtoList().get(0));
    }

    @Test
    void blockInvalidStatusExceptionAccountByProductIdAndStatusTest() {
        String productId = "1";
        String status = "Something wrong";
        assertThrows(InvalidSearchArgument.class, () -> service.blockAccountByProductIdAndStatus(productId, status));
    }

    @Test
    void blockInvalidProductIdExceptionAccountByProductIdAndStatusTest() {
        String productId = "Something wrong";
        String status = "ACTIVE";
        List<Account> accountList = new ArrayList<>();
        accountList.add(EntityCreator.getAccountEntity());
        Mockito.when(accountRepository.getAllByStatus(AccountStatus.ACTIVE)).thenReturn(accountList);
        assertThrows(InvalidSearchArgument.class, () -> service.blockAccountByProductIdAndStatus(productId, status));
    }

    @Test
    void blockAccountNotFoundExceptionAccountByProductIdAndStatusTest() {
        String productId = "2";
        String status = "ACTIVE";
        List<Account> accountList = new ArrayList<>();
        accountList.add(EntityCreator.getAccountEntity());
        Mockito.when(accountRepository.getAllByStatus(AccountStatus.ACTIVE)).thenReturn(accountList);
        assertThrows(AccountNotFoundException.class, () -> service.blockAccountByProductIdAndStatus(productId, status));
    }
}