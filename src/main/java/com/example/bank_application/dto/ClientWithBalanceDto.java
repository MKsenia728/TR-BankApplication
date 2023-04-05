package com.example.bank_application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ClientWithBalanceDto {
    String status;

    String taxCode;

    String firstName;

    String lastName;

     String email;

    String phone;

//    String balance;


}
