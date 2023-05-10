package com.accenture.books.services;

import com.accenture.books.dtos.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getBooks();

    BookDTO getBookByIsbn(String isbn);

    BookDTO addBook(BookDTO bookDTO, Long authorId);

    BookDTO updateBook(BookDTO bookDTO);

    void deleteBookByIsbn(String isbn);
}
