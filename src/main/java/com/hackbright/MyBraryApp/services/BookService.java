package com.hackbright.MyBraryApp.services;

import com.hackbright.MyBraryApp.dtos.BookDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface BookService {
    @Transactional
    void addBook(BookDto bookDto, Long userId);

    @Transactional
    void deleteBookById(Long bookId);

    @Transactional
    void updateBookById(BookDto bookDto);

    List<BookDto> getAllBooksByUserId(Long userId);

    Optional<BookDto> getBookById(Long bookId);
}
