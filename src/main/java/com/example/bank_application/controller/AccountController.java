package com.example.bank_application.controller;

import com.example.bank_application.dto.AccountAfterCreateUpdateDto;
import com.example.bank_application.dto.AccountCreateDto;
import com.example.bank_application.dto.AccountDto;
import com.example.bank_application.dto.AccountNameDto;
import com.example.bank_application.service.interf.AccountService;
import com.example.bank_application.validation.annotation.EnumAccountStatusOrNull;
import com.example.bank_application.validation.annotation.Iban;
import com.example.bank_application.validation.annotation.PositiveInteger;
import com.example.bank_application.validation.annotation.Uuid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Tag(name = "Accounts", description = "Controller for work with accounts")
public class AccountController {

    public final AccountService accountService;

    @GetMapping("/id/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Request for find account by ID",
            description = "If id exists - returns all info about account plus firstname and lastname of owner. " +
                    "If id does not exist - returns exception")
    @ApiResponse(responseCode = "200", description = "Successfully returned account by id", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AccountDto.class)))
    })
    public AccountDto getAccountById(@Uuid
                                     @Parameter(description = "Unique Id format UUID")
                                     @PathVariable("accountId") String accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping("/name/{accountName}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Request for find account by name",
            description = "If name exists - returns all info about account plus firstname and lastname of owner. " +
                    "If name does not exist - returns exception")
    @ApiResponse(responseCode = "200", description = "Successfully returned account by name", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AccountDto.class)))
    })
    public AccountDto getAccountByName(@Iban
                                       @Parameter(description = "Account`s name should be IBAN")
                                       @PathVariable("accountName") String accountName) {
        return accountService.getAccountByName(accountName);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Request for find all accounts",
            description = "If accounts exists - returns all names of accounts. " +
                    "If accounts do not exist - returns exception")
    @ApiResponse(responseCode = "200", description = "Successfully returned  all account`s name", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AccountNameDto.class)))
    })
    public List<@Valid AccountNameDto> getAllNameAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/all/{status}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Request for find all accounts by given status Active/Remote/Pending/",
            description = "If accounts exists - returns all accounts with all info of accounts according given status. " +
                    "If accounts do not exist - returns exception")
    @ApiResponse(responseCode = "200", description = "Successfully returned  all accounts by given status", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AccountDto.class)))
    })
    public List<AccountDto> getAllAccountsByStatus(@EnumAccountStatusOrNull
                                                   @Parameter(description = "Status should be PENDING / BLOCKED / ACTIVE")
                                                   @PathVariable("status") String status) {
        return accountService.getAllAccountsByStatus(status);
    }

    @PostMapping("new/client_tax/{clientTaxCode}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Request for create account by given client`s taxCode",
            description = "If client by taxCode exists and input JSON data is valid - creates and returns account with default fields" +
                    " (created_At, updated_At, balance, status, type) and input info. " +
                    "If client does not exist or data invalid or account already exists - returns server status code 400-406")
    @ApiResponse(responseCode = "200", description = "Successfully created account", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AccountAfterCreateUpdateDto.class)))
    })
    public AccountAfterCreateUpdateDto createNewAccount(
            @Valid
            @Parameter(description = "You can see schema for input data")
            @RequestBody AccountCreateDto accountCreateDto,
            @PathVariable("clientTaxCode") String clientTaxCode) {
        return accountService.createNewAccount(accountCreateDto, clientTaxCode);
    }

    @PutMapping("block_account/{productId}/{status}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Request for update all accounts by given status and product id",
            description = "If accounts exists - status changes to BLOCKED and returns all accounts with new info of accounts . " +
                    "If accounts do not exist - returns exception")
    @ApiResponse(responseCode = "200", description = "Successfully updated accounts by given status and product id", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AccountAfterCreateUpdateDto.class)))
    })
    public List<AccountAfterCreateUpdateDto> blockAccountByProductIdAndStatus(
            @PositiveInteger
            @Parameter(description = "Unique product id, format integer")
            @PathVariable("productId") String productId,
            @EnumAccountStatusOrNull
            @Parameter(description = "Status should be PENDING / BLOCKED / ACTIVE")
            @PathVariable("status") String status) {
        return accountService.blockAccountByProductIdAndStatus(productId, status);
    }
}
