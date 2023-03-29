package com.example.bank_application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

@Value
public class ManagerDto {
    Long id;
    String firstName;
    String lastName;
   String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    String createdAt;

    //    DtO: @JsonProperty("createdAt")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
//    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    LocalDateTime createdAt;


//    Timestamp updatedAt;
}
