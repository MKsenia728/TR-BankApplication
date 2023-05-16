package com.example.bank_application.controller.hanler;


import com.example.bank_application.dto.ErrorExtensionDto;
import com.example.bank_application.dto.ErrorResponseDto;
import com.example.bank_application.service.exceptions.DataAlreadyExistException;
import com.example.bank_application.service.exceptions.DataNotFoundException;
import com.example.bank_application.service.exceptions.ErrorMessage;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
        @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(ConstraintViolationException exception) {
        List<ErrorExtensionDto> extensions = exception.getConstraintViolations()
                .stream()
                .map(violation -> new ErrorExtensionDto(violation.getMessage(), ErrorMessage.INVALID_PATH_VARIABLE))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ErrorResponseDto(ErrorMessage.VALIDATION_FAILED, extensions), BAD_REQUEST);
    }
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorExtensionDto> handleNotFoundException(DataNotFoundException e) {
        log.error("", e);
        ErrorExtensionDto error = new ErrorExtensionDto(Integer.toString(HttpURLConnection.HTTP_NOT_FOUND), e.getMessage());
        return ResponseEntity.status(HttpURLConnection.HTTP_NOT_FOUND).body(error);
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorExtensionDto> handleDataAlreadyExistException(DataAlreadyExistException e) {
        log.error("", e);
        ErrorExtensionDto error = new ErrorExtensionDto(Integer.toString(HttpURLConnection.HTTP_NOT_ACCEPTABLE), e.getMessage());
        return ResponseEntity.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).body(error);
    }


    @ExceptionHandler
    public ResponseEntity<ErrorExtensionDto> handleUncaughtException(Throwable e) {
        String message = "This error is not provided";
        log.error(message, e);
        ErrorExtensionDto error = new ErrorExtensionDto(Integer.toString(HttpURLConnection.HTTP_INTERNAL_ERROR), message);
        return ResponseEntity.status(HttpURLConnection.HTTP_INTERNAL_ERROR).body(error);
    }
}
