package com.example.bank_application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.security.Timestamp;

@Value
public class AccountDto {

    String id;
    String name;
    String type;
    String status;
    String balance;
    String currencyCode;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    Timestamp createdAt;

    //    DtO: @JsonProperty("createdAt")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
//    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    LocalDateTime createdAt;


    Timestamp updatedAt;
}
