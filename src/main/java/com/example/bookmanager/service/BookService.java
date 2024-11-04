package com.example.bookmanager.service;

import com.example.bookmanager.request.BookRegistrationRequest;
import com.example.bookmanager.response.ApiResponse;
import com.example.bookmanager.response.BookResponse;
import org.springframework.http.ResponseEntity;

public interface BookService {

    ResponseEntity<ApiResponse<BookResponse>> addBook(BookRegistrationRequest request);

    ResponseEntity<ApiResponse<BookResponse>> getBookById(long id);
}
