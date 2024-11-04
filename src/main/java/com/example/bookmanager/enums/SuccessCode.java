package com.example.bookmanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    BOOK_ADDED(100, "Book was added"),

    BOOK_FOUND(101, "Book was found");

    private final int code;
    private final String message;

    @Override
    public String toString() {
        return code + " " + message;
    }
}
