package com.example.bookmanager.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@Builder
public class BookRequest {

    private Long id;

    @NotBlank(message = "title can't be empty")
    private String title;

    @NotBlank(message = "author can't be empty")
    private String author;

    @NotNull(message = "published date can't be empty")
    @Past(message = "published date must be in the past")
    private LocalDate publishedDate;
}
