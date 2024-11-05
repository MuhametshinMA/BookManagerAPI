package com.example.bookmanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    BOOK_ADDED(100, "Book was added"),

    BOOK_FOUND(101, "Book was found"),

    BOOK_UPDATED(102, "Book was updated"),

    BOOK_DELETED(103, "Book was deleted");

    private final int code;
    private final String message;

    @Override
    public String toString() {
        return code + " " + message;
    }
}
