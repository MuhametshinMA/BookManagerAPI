package com.example.bookmanager.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private String status;
    private String message;
    private final T data;
}
