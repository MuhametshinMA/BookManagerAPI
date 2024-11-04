package com.example.bookmanager.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private LocalDate published;
}
