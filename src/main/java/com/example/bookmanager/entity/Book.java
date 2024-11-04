package com.example.bookmanager.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book extends BaseEntity{
    private Long id;
    private String title;
    private String author;
    private LocalDate publishedDate;
}
