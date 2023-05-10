package com.accenture.books.daos.impl;

import com.accenture.books.daos.AuthorDAO;
import com.accenture.books.domain.Author;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorDAOImpl implements AuthorDAO {

    @Override
    public Optional<Author> findByBookIsbn(String isbn) {
        return Optional.empty();
    }
}
