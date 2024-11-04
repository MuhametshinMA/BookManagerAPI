package com.example.bookmanager.service.impl;

import com.example.bookmanager.entity.Book;
import com.example.bookmanager.enums.ErrorCode;
import com.example.bookmanager.enums.SuccessCode;
import com.example.bookmanager.exception.BusinessException;
import com.example.bookmanager.exception.handler.ExceptionHandler;
import com.example.bookmanager.rabbitmq.service.SenderService;
import com.example.bookmanager.repository.BookRepository;
import com.example.bookmanager.request.BookRegistrationRequest;
import com.example.bookmanager.response.ApiResponse;
import com.example.bookmanager.response.BookResponse;
import com.example.bookmanager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookRegistrationServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final SenderService senderService;

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<BookResponse>> addBook(BookRegistrationRequest request) {

        throwIfExists(request);

        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publishedDate(request.getPublishedDate())
                .build();

        Book savedBook = bookRepository.save(book);

        senderService.send(SuccessCode.BOOK_ADDED.toString());

        return ResponseEntity.ok(buildResponse(
                savedBook,
                HttpStatus.CREATED.toString(),
                SuccessCode.BOOK_ADDED.toString()
        ));
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<BookResponse>> getBookById(long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> ExceptionHandler
                        .raiseException(BusinessException.class, ErrorCode.BOOK_NOT_FOUND));

        return ResponseEntity.ok(buildResponse(
                book,
                HttpStatus.OK.toString(),
                SuccessCode.BOOK_FOUND.toString())
        );
    }

    private ApiResponse<BookResponse> buildResponse(Book book, String status, String message) {
        BookResponse response = BookResponse.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .title(book.getTitle())
                .published(book.getPublishedDate())
                .build();

        return ApiResponse.<BookResponse>builder()
                .data(response)
                .status(status)
                .message(message)
                .build();
    }

    private void throwIfExists(BookRegistrationRequest request) {
        Book existingBook = bookRepository.findByTitleAndAuthorAndPublishedDate(
                request.getTitle(),
                request.getAuthor(),
                request.getPublishedDate()
        );

        if (existingBook != null) {
            throw ExceptionHandler
                    .raiseException(BusinessException.class, ErrorCode.BOOK_EXISTS);
        }
    }
}
