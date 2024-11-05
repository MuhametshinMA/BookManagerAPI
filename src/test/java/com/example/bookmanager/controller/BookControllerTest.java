package com.example.bookmanager.controller;

import com.example.bookmanager.entity.Book;
import com.example.bookmanager.request.BookRequest;
import com.example.bookmanager.response.BookResponse;
import com.example.bookmanager.service.BookService;
import com.example.bookmanager.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@WebMvcTest(BookController.class)
//@SpringBootTest
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    BookController bookController;

    @Test
    public void testAddBook() {
//        BookRequest bookRequest = BookRequest.builder()
//                .title("1")
//                .author("1")
//                .publishedDate(LocalDate.of(1988, 10, 1))
//                .build();
//
//        Book book = Book.builder()
//                .title(bookRequest.getTitle())
//                .author(bookRequest.getAuthor())
//                .publishedDate(bookRequest.getPublishedDate())
//                .build();
//
//        BookResponse
//
//        when(bookService.addBook(bookRequest)).thenReturn(book);
    }

}
