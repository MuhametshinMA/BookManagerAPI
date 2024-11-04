package com.example.bookmanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    BOOK_EXISTS(1, "Book already exists"),

    VALIDATION_FAILED(2, "Request fields incorrect"),

    BOOK_NOT_FOUND(3, "Book not found");

    private final int code;

    private final String message;
}

