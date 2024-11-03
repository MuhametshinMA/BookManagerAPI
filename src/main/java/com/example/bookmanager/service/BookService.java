package com.example.bookmanager.service;

import com.example.bookmanager.request.BookRegistrationRequest;
import com.example.bookmanager.response.ApiResponse;
import com.example.bookmanager.response.BookRegistrationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface BookService {

    ResponseEntity<ApiResponse<BookRegistrationResponse>> addBook(BookRegistrationRequest request);
}
