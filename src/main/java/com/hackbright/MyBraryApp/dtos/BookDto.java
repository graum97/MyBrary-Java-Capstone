package com.hackbright.MyBraryApp.dtos;

import com.hackbright.MyBraryApp.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto implements Serializable {
    private Long id;
    private String title;
    private String author;
    private String genre;
    private String obtained;
    private Boolean read;
    private UserDto userDto;

    public BookDto(Book book) {
        if (book.getId() != null) {
            this.id = book.getId();
        }
        if (book.getTitle() != null) {
            this.title = book.getTitle();
        }
        if (book.getAuthor() != null) {
            this.author = book.getAuthor();
        }
        if (book.getGenre() != null) {
            this.genre = book.getGenre();
        }
        if (book.getObtained() != null) {
            this.obtained = book.getObtained();
        }
        if (book.getRead() != null) {
            this.read = book.getRead();
        }
    }
}
