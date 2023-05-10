package com.accenture.books.repositories;

import com.accenture.books.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> getBooks();

    Optional<Book> getBookByIsbn(String isbn);

    Book saveBook(Book book, Long authorId);

    Book updateBook(Book book);

    void deleteBookByIsbn(String isbn);
}
