package com.example.bookmanager.controller;

import com.example.bookmanager.request.BookRegistrationRequest;
import com.example.bookmanager.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<?>> addBook(@RequestBody BookRegistrationRequest request) {
        return null;
    }
}
