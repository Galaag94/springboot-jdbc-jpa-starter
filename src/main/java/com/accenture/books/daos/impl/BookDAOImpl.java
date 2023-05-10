package com.accenture.books.daos.impl;

import com.accenture.books.daos.BookDAO;
import com.accenture.books.domain.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAOImpl implements BookDAO {

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Optional<Book> getByIsbn(String isbn) {
        return Optional.empty();
    }

    @Override
    public Book insert(Book book, Long authorId) {
        return null;
    }

    @Override
    public Book update(Book book) {
        return null;
    }

    @Override
    public void deleteByIsbn(String isbn) {

    }
}
