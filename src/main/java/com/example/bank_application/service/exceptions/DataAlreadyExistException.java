package com.example.bank_application.service.exceptions;

public class DataAlreadyExistException extends RuntimeException{
    public DataAlreadyExistException(String message) {
        super(message);
    }
}
