package com.accenture.books.services.impl;

import com.accenture.books.dtos.BookDTO;
import com.accenture.books.dtos.mappers.AuthorMapper;
import com.accenture.books.dtos.mappers.BookMapper;
import com.accenture.books.exceptions.NotFoundException;
import com.accenture.books.repositories.AuthorJpaRepository;
import com.accenture.books.repositories.BookJpaRepository;
import com.accenture.books.repositories.impl.BookJDBCRepository;
import com.accenture.books.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;
    private final BookJDBCRepository bookJDBCRepository;
    private final BookJpaRepository bookJpaRepository;
    private final AuthorJpaRepository authorJpaRepository;

    public BookServiceImpl(
            BookMapper bookMapper,
            AuthorMapper authorMapper, BookJDBCRepository bookJDBCRepository,
            BookJpaRepository bookJpaRepository,
            AuthorJpaRepository authorJpaRepository
    ) {
        this.bookMapper = bookMapper;
        this.authorMapper = authorMapper;
        this.bookJDBCRepository = bookJDBCRepository;
        this.bookJpaRepository = bookJpaRepository;
        this.authorJpaRepository = authorJpaRepository;
    }

    @Override
    public List<BookDTO> getBooks() {
        /*return bookJDBCRepository.getBooks()
                .stream()
                .map(bookMapper::toDTO)
                .toList();*/
        return bookJpaRepository.findAll()
                .stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @Override
    public BookDTO getBookByIsbn(String isbn) {
        /*var book = bookJDBCRepository.getBookByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException(String.format("No book with isbn: %s was not found", isbn)));*/

        var book = bookJpaRepository.findById(isbn)
                .orElseThrow(() -> new NotFoundException(String.format("No book with isbn: %s was not found", isbn)));

        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO, Long authorId) {
        /*var saved = bookJDBCRepository.saveBook(bookMapper.toModel(bookDTO), authorId);*/

        var bookEntity = bookMapper.toEntity(bookDTO);
        bookEntity.setAuthor(authorJpaRepository.getReferenceById(authorId));

        var saved = bookJpaRepository.save(bookEntity);

        return bookMapper.toDTO(saved);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO, String isbn) {
        /*var book = bookJDBCRepository.getBookByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException(String.format("No book with isbn: %s was found", isbn)));

        book.setTitle(bookDTO.title());

         if (bookDTO.author() != null) {
             book.setAuthor(authorMapper.toModel(bookDTO.author()));
         }

        var updated = bookJDBCRepository.updateBook(book);*/

        if (bookJpaRepository.existsById(isbn)) {
            throw new NotFoundException(String.format("No book with isbn: %s was found", isbn));
        }

        var updated = bookJpaRepository.save(bookMapper.toEntity(bookDTO));

        return bookMapper.toDTO(updated);
    }

    @Override
    public void deleteBookByIsbn(String isbn) {
        //bookJDBCRepository.deleteBookByIsbn(isbn);
        bookJpaRepository.deleteById(isbn);
    }
}
