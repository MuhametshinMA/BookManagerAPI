package com.example.bookmanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BOOK_EXISTS(1, "Book already exists");

    private final int code;

    private final String message;
}

