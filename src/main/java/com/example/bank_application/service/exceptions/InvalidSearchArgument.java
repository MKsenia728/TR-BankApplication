package com.example.bank_application.service.exceptions;

public class InvalidSearchArgument extends IllegalArgumentException{
    public InvalidSearchArgument(String message) {
        super(message);
    }
}