package com.hackbright.MyBraryApp.services;

import com.hackbright.MyBraryApp.dtos.BookDto;
import com.hackbright.MyBraryApp.entities.Book;
import com.hackbright.MyBraryApp.entities.User;
import com.hackbright.MyBraryApp.repositories.BookRepository;
import com.hackbright.MyBraryApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public void addBook(BookDto bookDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Book book = new Book(bookDto);
        userOptional.ifPresent(book::setUser);
        bookRepository.saveAndFlush(book);
    }

    @Override
    @Transactional
    public void deleteBookById(Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        bookOptional.ifPresent(book -> bookRepository.delete(book));
    }

    @Override
    @Transactional
    public void moveBookById(BookDto bookDto) {
        Optional<Book> bookOptional = bookRepository.findById(bookDto.getId());
        bookOptional.ifPresent(book -> {
            book.setRead(bookDto.getRead());
            bookRepository.saveAndFlush(book);
        });
    }

    @Override
    public List<BookDto> getAllBooksByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            List<Book> bookList = bookRepository.findAllByUserEquals(userOptional.get());
            return bookList.stream().map(book -> new BookDto(book)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<BookDto> getBookById(Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            return Optional.of(new BookDto(bookOptional.get()));
        }
        return Optional.empty();
    }
}
