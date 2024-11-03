package com.example.bookmanager.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class BookRegistrationResponse {
    private Long id;
    private String title;
    private String author;
    private LocalDate published;
}
