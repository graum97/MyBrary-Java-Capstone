package com.hackbright.MyBraryApp.repositories;

import com.hackbright.MyBraryApp.entities.Book;
import com.hackbright.MyBraryApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByUserEquals(User user);

    Optional<Book> findByGenre(String bookGenre);
}
