package com.example.bookmanager.controller;

import com.example.bookmanager.entity.Book;
import com.example.bookmanager.enums.SuccessCode;
import com.example.bookmanager.request.BookRequest;
import com.example.bookmanager.response.ApiResponse;
import com.example.bookmanager.response.BookResponse;
import com.example.bookmanager.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testAddBook() throws Exception {
        BookRequest bookRequest = BookRequest.builder()
                .title("1")
                .author("1")
                .publishedDate(LocalDate.of(1988, 10, 1))
                .build();

        Book book = Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .publishedDate(bookRequest.getPublishedDate())
                .build();

        BookResponse response = BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .published(book.getPublishedDate())
                .build();
        ApiResponse<BookResponse> apiResponse = ApiResponse.<BookResponse>builder()
                .data(response)
                .message(HttpStatus.CREATED.toString())
                .status(SuccessCode.BOOK_ADDED.toString())
                .build();

        when(bookService.addBook(bookRequest)).thenReturn(ResponseEntity.ok(apiResponse));

        mockMvc.perform(post("/api/v1/book/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"1\", \"author\":\"1\", \"publishedDate\":\"1988-10-01\"}"))
                .andExpect(status().isOk());

    }

}
