package com.example.bookmanager.service.impl;

import com.example.bookmanager.entity.Book;
import com.example.bookmanager.enums.ErrorCode;
import com.example.bookmanager.enums.SuccessCode;
import com.example.bookmanager.exception.BusinessException;
import com.example.bookmanager.exception.handler.ExceptionHandler;
import com.example.bookmanager.rabbitmq.service.SenderService;
import com.example.bookmanager.repository.BookRepository;
import com.example.bookmanager.request.BookRequest;
import com.example.bookmanager.response.ApiResponse;
import com.example.bookmanager.response.BookResponse;
import com.example.bookmanager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<ApiResponse<BookResponse>> addBook(BookRequest request) {

        throwIfExists(request);

        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publishedDate(request.getPublishedDate())
                .build();

        Book savedBook = bookRepository.save(book);

        senderService.send(buildSendMessage(book, SuccessCode.BOOK_ADDED.toString()));

        return ResponseEntity.ok(buildResponse(
                savedBook,
                HttpStatus.CREATED.toString(),
                SuccessCode.BOOK_ADDED.toString()
        ));
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<BookResponse>> getBookById(long id) {

        Book book = getBookOrThrow(id);

        return ResponseEntity.ok(buildResponse(
                book,
                HttpStatus.OK.toString(),
                SuccessCode.BOOK_FOUND.toString())
        );
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<BookResponse>> updateBook(BookRequest request) {

        if (request.getId() == null) ExceptionHandler
                .raiseException(BusinessException.class, ErrorCode.INVALID_BOOK_ID);

        Book book = getBookOrThrow(request.getId());

        if (book == null) {
            throw ExceptionHandler
                    .raiseException(BusinessException.class, ErrorCode.BOOK_NOT_FOUND);
        }

        Book toUpdate = Book.builder()
                .id(request.getId())
                .author(request.getAuthor())
                .title(request.getTitle())
                .publishedDate(request.getPublishedDate())
                .build();

        Book updatedBook = bookRepository.save(toUpdate);

        senderService.send(buildSendMessage(toUpdate, SuccessCode.BOOK_UPDATED.toString()));

        return ResponseEntity.ok(buildResponse(
                updatedBook,
                HttpStatus.OK.toString(),
                SuccessCode.BOOK_UPDATED.toString()
        ));
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<BookResponse>> deleteBook(long id) {

        Book book = getBookOrThrow(id);

        senderService.send(buildSendMessage(book, SuccessCode.BOOK_DELETED.toString()));

        bookRepository.delete(book);

        return ResponseEntity.ok(buildResponse(
                book,
                HttpStatus.OK.toString(),
                SuccessCode.BOOK_DELETED.toString()
        ));
    }

    @Override
    public ResponseEntity<ApiResponse<Page<BookResponse>>> getAllBook(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = bookRepository.findAll(pageable);

        if (bookPage.isEmpty()) ExceptionHandler
                .raiseException(BusinessException.class, ErrorCode.BOOK_NOT_FOUND);

        Page<BookResponse> bookResponses = bookPage.map(book -> new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublishedDate()
        ));

        ApiResponse<Page<BookResponse>> response = ApiResponse.<Page<BookResponse>>builder()
                .data(bookResponses)
                .status(HttpStatus.OK.toString())
                .message(SuccessCode.BOOK_FOUND.toString())
                .build();

        return ResponseEntity.ok(response);
    }

    private String buildSendMessage(Book book, String status) {
        return book + ": " + status;
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

    private void throwIfExists(BookRequest request) {

        Book book = getBook(request);
        if (book != null) {
            throw ExceptionHandler
                    .raiseException(BusinessException.class, ErrorCode.BOOK_EXISTS);
        }
    }

    private Book getBookOrThrow(Long id) {

        return bookRepository.findById(id)
                .orElseThrow(() -> ExceptionHandler
                        .raiseException(BusinessException.class, ErrorCode.BOOK_NOT_FOUND));
    }

    private Book getBook(BookRequest request) {

        return bookRepository.findByTitleAndAuthorAndPublishedDate(
                request.getTitle(),
                request.getAuthor(),
                request.getPublishedDate()
        );
    }
}
