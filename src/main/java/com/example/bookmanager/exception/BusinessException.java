package com.example.bookmanager.exception;

import com.example.bookmanager.enums.ErrorCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BusinessException extends RuntimeException implements BookErrors {

    private final ErrorCode errorCode;

    @Override
    public ErrorCode getErrorCode() {
        return null;
    }

    @Override
    public String toString() {
        return "BusinessException " + errorCode.getCode() + ": " + errorCode.getMessage();
    }
}
