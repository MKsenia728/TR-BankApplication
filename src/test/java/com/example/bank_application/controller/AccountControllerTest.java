package com.example.bank_application.controller;

import com.example.bank_application.dto.accountDto.AccountAfterCreateDto;
import com.example.bank_application.dto.accountDto.AccountCreateDto;
import com.example.bank_application.dto.accountDto.AccountDto;
import com.example.bank_application.service.exceptions.DataNotFoundException;
import com.example.bank_application.service.exceptions.ErrorMessage;
import com.example.bank_application.service.exceptions.InvalidSearchArgumentException;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccountController.class)
@DisplayName("AccountController test class")
class AccountControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AccountService service;

    @Autowired
    MockMvc mockMvc;

    private final String status = "ACTIVE";

    @DisplayName("Positive test. Server status 200. Controller for find account by ID")
    @Test
    void getAccountByIdStatusTest() throws Exception {
        String id = EntityCreator.UUID;
        mockMvc.perform(get("/accounts/id/"+id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("Positive test. JSON response. Controller for find account by ID")
    @Test
    public void getAccountByIdResponseTest() throws Exception {
        String id = EntityCreator.UUID;
        AccountDto expectAccount = DtoCreator.getAccountDto();
        Mockito.when(service.getAccountById(id)).thenReturn(expectAccount);

        mockMvc.perform(get("/accounts/id/"+id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(expectAccount.getId()))
                .andExpect(jsonPath("$.name").value(expectAccount.getName()))
                .andExpect(jsonPath("$.type").value(expectAccount.getType()))
                .andExpect(jsonPath("$.status").value(expectAccount.getStatus()))
                .andExpect(jsonPath("$.balance").value(expectAccount.getBalance()))
                .andExpect(jsonPath("$.currencyCode").value(expectAccount.getCurrencyCode()))
                .andExpect(jsonPath("$.clientFirstName").value(expectAccount.getClientFirstName()))
                .andExpect(jsonPath("$.clientLastName").value(expectAccount.getClientLastName()));
    }

    @DisplayName("Negative test.Controller for find account by ID, but ID is invalid")
    @Test
    public void getAccountByIdInvalidIdExceptionTest() throws Exception {
        String id = EntityCreator.UUID;
        Mockito.when(service.getAccountById(id)).thenThrow( new DataNotFoundException((ErrorMessage.ACCOUNT_NOT_FOUND)));
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/id/"+id))
//                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
//                .andExpect(jsonPath("$.message").value(ErrorMessage.ACCOUNT_NOT_FOUND));
    }

    @DisplayName("Positive test. Server status 200. Controller for find all accounts")
    @Test
    void getAllAccountsStatusTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("Positive test. JSON response. Controller for find all accounts")
    @Test
    void getAllAccountsResponseTest() throws Exception {
        List<AccountDto> accountDtoList = new ArrayList<>();
        accountDtoList.add(DtoCreator.getAccountDto());

        Mockito.when(service.getAllAccounts()).thenReturn(accountDtoList);

        mockMvc.perform(
                        get("/accounts/all")
                                .accept(MediaType.APPLICATION_JSON))
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

    @DisplayName("Positive test. Server status 200. Controller for find all accounts by given status")
    @Test
    void getAllAccountsByStatusTest() throws Exception {
        mockMvc.perform(get("/accounts/all/" + status)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @DisplayName("Positive test. JSON response. Controller for find all accounts by given status")
    @Test
    void getAllAccountsByStatusResponseTest() throws Exception {
        List<AccountDto> accountDtoList = new ArrayList<>();
        accountDtoList.add(DtoCreator.getAccountDto());

        Mockito.when(service.getAllAccountsByStatus(status)).thenReturn(accountDtoList);

        mockMvc.perform(
                        get("/accounts/all/"+status)
                                .accept(MediaType.APPLICATION_JSON))
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


    @DisplayName("Positive test. Server status 200. Controller for create account by given client tax")
    @Test
    void createNewAccountStatusTest() throws Exception {
        String taxCode = "123123123123";
        AccountCreateDto accountCreateDto = DtoCreator.getAccountCreateDto();
        mockMvc.perform(post("/accounts/new/client_tax/" + taxCode)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(accountCreateDto))
                )
                .andExpect(status().isCreated());
    }

    @DisplayName("Positive test. JSON response. Controller for create account by given client tax")
    @Test
    void createNewAccountTest() throws Exception {
        String taxCode = "123123123123";
        AccountCreateDto accountCreateDto = DtoCreator.getAccountCreateDto();
        AccountAfterCreateDto accountAfterCreateDto = DtoCreator.getAccountAfterCreateDto("PENDING");
        Mockito.when(service.createNewAccount(accountCreateDto, taxCode)).thenReturn(accountAfterCreateDto);
        mockMvc.perform(post("/accounts/new/client_tax/" + taxCode)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountCreateDto))
                )
                .andExpect(status().isCreated())
               .andExpect(jsonPath("$.name").value(accountAfterCreateDto.getName()))
               .andExpect(jsonPath("$.type").value(accountAfterCreateDto.getType()))
               .andExpect(jsonPath("$.status").value(accountAfterCreateDto.getStatus()))
               .andExpect(jsonPath("$.balance").value(accountAfterCreateDto.getBalance()))
               .andExpect(jsonPath("$.currencyCode").value(accountAfterCreateDto.getCurrencyCode()));

        Mockito.verify(service).createNewAccount(accountCreateDto, taxCode);
    }

    @DisplayName("Negative test. Not enough input data. Controller for create account by given client tax")
    @Test
    void createNewAccountNotEnoughDataTest() throws Exception {
        String taxCode = "123123123123";
        AccountCreateDto accountCreateDto = DtoCreator.getAccountCreateDtoWithoutNecessaryData();
        mockMvc.perform(post("/accounts/new/client_tax/" + taxCode)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountCreateDto))
                )
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Negative test. Not enough input data. Controller for create account by given client tax")
    @Test
    void createNewAccountInvalidStatusTest() throws Exception {
        String taxCode = "123123123123";
        AccountCreateDto accountCreateDto = DtoCreator.getAccountCreateDtoWithInvalidStatus();
        mockMvc.perform(post("/accounts/new/client_tax/" + taxCode)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountCreateDto))
                )
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Positive test. Server status 200. Controller for update account by given account status and product id")
    @Test
    void blockAccountByProductIdAndStatusTest() throws Exception {
        String productId = "1";
        AccountAfterCreateDto accountAfterCreateDto = DtoCreator.getAccountAfterCreateDto("BLOCKED");
        List<AccountAfterCreateDto> resultListDto = new ArrayList<>();
        resultListDto.add(accountAfterCreateDto);
        Mockito.when(service.blockAccountByProductIdAndStatus(productId, status)).thenReturn(resultListDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/accounts/block_account/" + productId + "/" + status)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resultListDto)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("BLOCKED"));
    }
}