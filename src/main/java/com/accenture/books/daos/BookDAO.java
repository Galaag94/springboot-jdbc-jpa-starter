package com.accenture.books.daos;

import com.accenture.books.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDAO {

    List<Book> getAll();

    Optional<Book> getByIsbn(String isbn);

    Book insert(Book book, Long authorId);

    Book update(Book book);

    void deleteByIsbn(String isbn);
}
