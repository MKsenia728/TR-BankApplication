package com.example.bank_application.controller;

import com.example.bank_application.dto.accountDto.AccountDto;
import com.example.bank_application.service.interf.AccountService;
import com.example.bank_application.service.util.DtoCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@AutoConfigureMockMvc
@WebMvcTest(AccountController.class)
@DisplayName("AccountController test class")
class AccountControllerTest {
    @MockBean
    AccountService accountService;
    @Autowired
    AccountController accountController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAccountByIdTest() {
    }

//    @Test
//    void getAllAccountsTest() {
//        List<AccountDto> accountDtoList = new ArrayList<>();
//        AccountDto accountDto = DtoCreator.getDto();
//        accountDtoList.add(accountDto);
//
//        Mockito.when(accountService.getAllAccounts()).thenReturn(accountDtoList);
//        mockMvc.perform(MockMvcRequestBuilders.get("accounts/all"))
//                .andExpect(jsonPath("$.name").value(accountDtoList.get(0).getName()))
//                .andExpect(jsonPath("$.type").value(accountDtoList.get(0).getType()))
//    }

    @Test
    void getAllAccountsByStatusTest() {
    }

    @Test
    void createNewAccountTest() {
    }

    @Test
    void blockAccountByProductIdAndStatusTest() {
    }
}