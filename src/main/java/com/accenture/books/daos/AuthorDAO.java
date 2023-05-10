package com.accenture.books.daos;

import com.accenture.books.domain.Author;

import java.util.Optional;

public interface AuthorDAO {

    Optional<Author> findByBookIsbn(String isbn);
}
