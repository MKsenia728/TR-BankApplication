package com.example.bank_application.controller;

import com.example.bank_application.dto.accountDto.AccountAfterCreateDto;
import com.example.bank_application.dto.accountDto.AccountCreateDto;
import com.example.bank_application.dto.accountDto.AccountDto;
import com.example.bank_application.entity.Account;
import com.example.bank_application.service.interf.AccountService;
import com.example.bank_application.util.DtoCreator;
import com.example.bank_application.util.EntityCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@DisplayName("AccountController test class")
class AccountControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AccountService service;

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Positive test. Controller for find account by ID")
    @Test
    void getAccountByIdTest() throws Exception {
        AccountDto accountDto = DtoCreator.getAccountDto();
        String id = accountDto.getId();
        Mockito.when(service.getAccountById(id)).thenReturn(accountDto);

        mockMvc.perform(get("/accounts/id/00001d7f-d14f-4655-9399-25bf27b16588"))
                .andExpect(status().isOk())
//                .andExpect(header().string("Cache-Control", "no-cache"))
                .andExpect(jsonPath("$.id").value(accountDto.getId()))
                .andExpect(jsonPath("$.name").value(accountDto.getName()))
                .andExpect(jsonPath("$.type").value(accountDto.getType()))
                .andExpect(jsonPath("$.status").value(accountDto.getStatus()))
                .andExpect(jsonPath("$.balance").value(accountDto.getBalance()))
                .andExpect(jsonPath("$.currencyCode").value(accountDto.getCurrencyCode()))
                .andExpect(jsonPath("$.clientFirstName").value(accountDto.getClientFirstName()))
                .andExpect(jsonPath("$.clientLastName").value(accountDto.getClientLastName()));

        Mockito.verify(service).getAccountById(id);
    }
    @DisplayName("Positive test. Controller for find all accounts")
    @Test
    void getAllAccountsTest() throws Exception {
        List<AccountDto> accountDtoList = new ArrayList<>();
        accountDtoList.add(DtoCreator.getAccountDto());

        Mockito.when(service.getAllAccounts()).thenReturn(accountDtoList);

        mockMvc.perform(
                        get("/accounts/all")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(accountDtoList.get(0).getId()))
                .andExpect(jsonPath("$[0].name").value(accountDtoList.get(0).getName()))
                .andExpect(jsonPath("$[0].type").value(accountDtoList.get(0).getType()))
                .andExpect(jsonPath("$[0].status").value(accountDtoList.get(0).getStatus()))
                .andExpect(jsonPath("$[0].balance").value(accountDtoList.get(0).getBalance()))
                .andExpect(jsonPath("$[0].currencyCode").value(accountDtoList.get(0).getCurrencyCode()))
                .andExpect(jsonPath("$[0].clientFirstName").value(accountDtoList.get(0).getClientFirstName()))
                .andExpect(jsonPath("$[0].clientLastName").value(accountDtoList.get(0).getClientLastName()));
        Mockito.verify(service).getAllAccounts();
    }
    @DisplayName("Positive test. Controller for find all accounts by given status")
    @Test
    void getAllAccountsByStatusTest() throws Exception {
        List<AccountDto> accountDtoList = new ArrayList<>();
        accountDtoList.add(DtoCreator.getAccountDto());
        String status = "ACTIVE";

        Mockito.when(service.getAllAccountsByStatus(status)).thenReturn(accountDtoList);

        mockMvc.perform(
                        get("/accounts/all/ACTIVE")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(accountDtoList.get(0).getId()))
                .andExpect(jsonPath("$[0].name").value(accountDtoList.get(0).getName()))
                .andExpect(jsonPath("$[0].type").value(accountDtoList.get(0).getType()))
                .andExpect(jsonPath("$[0].status").value(accountDtoList.get(0).getStatus()))
                .andExpect(jsonPath("$[0].status").value(status))
                .andExpect(jsonPath("$[0].balance").value(accountDtoList.get(0).getBalance()))
                .andExpect(jsonPath("$[0].currencyCode").value(accountDtoList.get(0).getCurrencyCode()))
                .andExpect(jsonPath("$[0].clientFirstName").value(accountDtoList.get(0).getClientFirstName()))
                .andExpect(jsonPath("$[0].clientLastName").value(accountDtoList.get(0).getClientLastName()));
        Mockito.verify(service).getAllAccountsByStatus(status);
    }

//    @Test
//    void getExceptionAllAccountsByStatusTest() throws Exception {
//        List<AccountDto> accountDtoList = new ArrayList<>();
//        accountDtoList.add(DtoCreator.getAccountDto());
//        String status = "PENDING";
//        Mockito.when(service.getAllAccountsByStatus(status)).thenThrow(new DataNotFoundException(ErrorMessage.ACCOUNTS_NOT_FOUND_BY_STATUS));
//
//        mockMvc.perform(get("/accounts/all/PENDING"))
//                .andExpect(status().isBadRequest());
//    }


    @Test
    void createNewAccountTest() throws Exception {
        String taxCode = "123123123123";
        Account account = EntityCreator.getCreateAccountEntity();
        AccountCreateDto accountCreateDto = DtoCreator.getAccountCreateDto();
        AccountAfterCreateDto accountAfterCreateDto = DtoCreator.getAccountAfterCreateDto("PENDING");
        Mockito.when(service.createNewAccount(accountCreateDto, taxCode)).thenReturn(accountAfterCreateDto);
        mockMvc.perform(post("/accounts/new/client_tax/123123123123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account))
                )
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.name").value(account.getName()));
        service.createNewAccount(accountCreateDto, taxCode);
        Mockito.verify(service).createNewAccount(accountCreateDto, taxCode);

    }

    @Test
    void blockAccountByProductIdAndStatusTest() {
    }
}