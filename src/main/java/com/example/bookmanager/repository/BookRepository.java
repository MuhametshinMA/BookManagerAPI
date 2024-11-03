package com.example.bookmanager.repository;

import com.example.bookmanager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitleAndAuthorAndPublishedDate(String title, String author, LocalDate published);
}
