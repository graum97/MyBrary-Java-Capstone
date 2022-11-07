package com.hackbright.MyBraryApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hackbright.MyBraryApp.dtos.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String genre;

    @Column
    private String obtained;

    @Column
    private Boolean read;

    @ManyToOne
    @JsonBackReference
    private User user;

    public Book(BookDto bookDto) {
        if (bookDto.getTitle() != null) {
            this.title = bookDto.getTitle();
        }
        if (bookDto.getAuthor() != null) {
            this.author = bookDto.getAuthor();
        }
        if (bookDto.getGenre() != null) {
            this.genre = bookDto.getGenre();
        }
        if (bookDto.getObtained() != null) {
            this.obtained = bookDto.getObtained();
        }
        if (bookDto.getRead() != null) {
            this.read = bookDto.getRead();
        }
    }
}
