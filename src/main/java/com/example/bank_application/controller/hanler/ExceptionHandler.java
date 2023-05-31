package com.example.bank_application.controller.hanler;


import com.example.bank_application.dto.ErrorExtensionDto;
import com.example.bank_application.dto.ErrorResponseDto;
import com.example.bank_application.service.exceptions.DataAlreadyExistException;
import com.example.bank_application.service.exceptions.DataNotFoundException;
import com.example.bank_application.service.exceptions.ErrorMessage;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(ConstraintViolationException exception) {
        List<ErrorExtensionDto> extensions = exception.getConstraintViolations()
                .stream()
                .map(violation -> new ErrorExtensionDto(violation.getMessage(), ErrorMessage.INVALID_PATH_VARIABLE))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ErrorResponseDto(ErrorMessage.VALIDATION_FAILED, extensions), BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorExtensionDto> handleNotFoundException(DataNotFoundException e) {
        log.error("", e);
        ErrorExtensionDto error = new ErrorExtensionDto(Integer.toString(HttpURLConnection.HTTP_NOT_FOUND), e.getMessage());
        return ResponseEntity.status(HttpURLConnection.HTTP_NOT_FOUND).body(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<ErrorExtensionDto> handleDataAlreadyExistException(DataAlreadyExistException e) {
        log.error("", e);
        ErrorExtensionDto error = new ErrorExtensionDto(Integer.toString(HttpURLConnection.HTTP_NOT_ACCEPTABLE), e.getMessage());
        return ResponseEntity.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).body(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();

        for (FieldError error : result.getFieldErrors()) {
            errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
        }
        return ResponseEntity.badRequest().body(errorMessage.toString());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorExtensionDto> handleUncaughtException(Throwable e) {
        String message = "This error is not provided";
        log.error(message, e);
        ErrorExtensionDto error = new ErrorExtensionDto(Integer.toString(HttpURLConnection.HTTP_INTERNAL_ERROR), message);
        return ResponseEntity.status(HttpURLConnection.HTTP_INTERNAL_ERROR).body(error);
    }
}
