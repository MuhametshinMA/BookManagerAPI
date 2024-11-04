package com.example.bookmanager.controller;

import com.example.bookmanager.request.BookRegistrationRequest;
import com.example.bookmanager.response.ApiResponse;
import com.example.bookmanager.response.BookResponse;
import com.example.bookmanager.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<BookResponse>> addBook(@RequestBody @Valid BookRegistrationRequest request) {
        return bookService.addBook(request);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> getBookById(@PathVariable long id) {
        return bookService.getBookById(id);
    }
}
