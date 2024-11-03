package com.example.bookmanager.service;

import com.example.bookmanager.entity.Book;
import com.example.bookmanager.enums.ErrorCode;
import com.example.bookmanager.exception.BussinessException;
import com.example.bookmanager.exception.handler.ExceptionHandler;
import com.example.bookmanager.repository.BookRepository;
import com.example.bookmanager.request.BookRegistrationRequest;
import com.example.bookmanager.response.ApiResponse;
import com.example.bookmanager.response.BookRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookRegistrationServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<BookRegistrationResponse>> addBook(BookRegistrationRequest request) {

        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publishedDate(request.getPublishedDate())
                .build();

        Book existingBook = bookRepository.findByTitleAndAuthorAndPublishedDate(
                request.getTitle(),
                request.getAuthor(),
                request.getPublishedDate()
        );

        if (existingBook != null) {
            throw ExceptionHandler.raiseException(BussinessException.class, ErrorCode.BOOK_EXISTS);
        }

        Book savedBook = bookRepository.save(book);


        return null;
    }
}
