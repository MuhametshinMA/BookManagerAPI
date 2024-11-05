package com.example.bookmanager.controller;

import com.example.bookmanager.request.BookRequest;
import com.example.bookmanager.response.ApiResponse;
import com.example.bookmanager.response.BookResponse;
import com.example.bookmanager.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<BookResponse>> addBook(@RequestBody @Valid BookRequest request) {
        return bookService.addBook(request);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> getBookById(@PathVariable long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse<BookResponse>> updateBook(@RequestBody @Valid BookRequest request) {
        return bookService.updateBook(request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> deleteBook(@PathVariable long id) {
        return bookService.deleteBook(id);
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<Page<BookResponse>>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return bookService.getAllBook(page, size);
    }


}
