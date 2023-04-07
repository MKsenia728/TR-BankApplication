package com.example.bank_application.service.exceptions;

public class InvalidSearchArgument extends RuntimeException{
    public InvalidSearchArgument(String message) {
        super(message);
    }
}
