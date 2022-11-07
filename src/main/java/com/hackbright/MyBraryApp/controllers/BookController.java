package com.hackbright.MyBraryApp.controllers;

import com.hackbright.MyBraryApp.dtos.BookDto;
import com.hackbright.MyBraryApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/user/{userId}")
    public List<BookDto> getBooksByUser(@PathVariable Long userId) {
        return bookService.getAllBooksByUserId(userId);
    }

    @GetMapping("/{noteId}")
    public Optional<BookDto> getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping("/user/{userId}")
    public void addBook(@RequestBody BookDto bookDto, @PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
    }

    @PutMapping
    public void updateBook(@RequestBody BookDto bookDto) {
        bookService.updateBookById(bookDto);
    }
}