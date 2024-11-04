package com.example.bookmanager.controller;

import com.example.bookmanager.enums.ErrorCode;
import com.example.bookmanager.exception.BusinessException;
import com.example.bookmanager.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<String>> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ApiResponse<String> response = ApiResponse.<String>builder()
                .status(HttpStatus.BAD_REQUEST.toString())
                .message(ErrorCode.VALIDATION_FAILED.toString())
                .data(errorMessage)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiResponse<String>> handleBusinessError(BusinessException ex) {
        String error = ex.toString();

        ApiResponse<String> response = ApiResponse.<String>builder()
                .status(HttpStatus.CONFLICT.toString())
                .message(ErrorCode.BOOK_EXISTS.getMessage())
                .data(error)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
}
