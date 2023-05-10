package com.accenture.books.services.impl;

import com.accenture.books.dtos.BookDTO;
import com.accenture.books.dtos.mappers.BookMapper;
import com.accenture.books.exceptions.NotFoundException;
import com.accenture.books.repositories.impl.BookJDBCRepository;
import com.accenture.books.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BookJDBCRepository bookJDBCRepository;
    private final BookJPARepository bookJPARepository;

    public BookServiceImpl(
            BookMapper bookMapper,
            BookJDBCRepository bookJDBCRepository,
            BookJPARepository bookJPARepository
    ) {
        this.bookMapper = bookMapper;
        this.bookJDBCRepository = bookJDBCRepository;
        this.bookJPARepository = bookJPARepository;
    }

    @Override
    public List<BookDTO> getBooks() {
        return bookJDBCRepository.getBooks()
                .stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @Override
    public BookDTO getBookByIsbn(String isbn) {
        var book = bookJDBCRepository.getBookByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException(String.format("No book with isbn: %s was not found", isbn)));

        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO, Long authorId) {
        var saved = bookJDBCRepository.saveBook(bookMapper.toModel(bookDTO), authorId);

        return bookMapper.toDTO(saved);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) {
        var updated = bookJDBCRepository.updateBook(bookMapper.toModel(bookDTO));

        return bookMapper.toDTO(updated);
    }

    @Override
    public void deleteBookByIsbn(String isbn) {
        bookJDBCRepository.deleteBookByIsbn(isbn);
    }
}
