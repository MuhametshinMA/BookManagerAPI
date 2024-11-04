package com.example.bookmanager.service;

import com.example.bookmanager.request.BookRequest;
import com.example.bookmanager.response.ApiResponse;
import com.example.bookmanager.response.BookResponse;
import org.springframework.http.ResponseEntity;

public interface BookService {

    ResponseEntity<ApiResponse<BookResponse>> addBook(BookRequest request);

    ResponseEntity<ApiResponse<BookResponse>> getBookById(long id);

    ResponseEntity<ApiResponse<BookResponse>> updateBook(BookRequest request);

    ResponseEntity<ApiResponse<BookResponse>> deleteBook(long id);
}
