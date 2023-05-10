package com.accenture.books.daos;

import com.accenture.books.domain.Author;

public interface AuthorDAO {

    Author findByBookIsbn(String isbn);
}
