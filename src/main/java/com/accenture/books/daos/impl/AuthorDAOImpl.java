package com.accenture.books.daos.impl;

import com.accenture.books.daos.AuthorDAO;
import com.accenture.books.domain.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDAOImpl implements AuthorDAO {
    @Override
    public Author findByBookIsbn(String isbn) {
        return null;
    }
}
