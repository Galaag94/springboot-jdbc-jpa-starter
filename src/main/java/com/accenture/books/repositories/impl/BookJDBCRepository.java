package com.accenture.books.repositories.impl;

import com.accenture.books.daos.AuthorDAO;
import com.accenture.books.daos.BookDAO;
import com.accenture.books.domain.Book;
import com.accenture.books.exceptions.BookOperationException;
import com.accenture.books.repositories.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BookJDBCRepository implements BookRepository {

    private final BookDAO bookDAO;
    private final AuthorDAO authorDAO;

    public BookJDBCRepository(BookDAO bookDAO, AuthorDAO authorDAO) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
    }

    @Override
    public List<Book> getBooks() {
        return bookDAO.getAll()
                .stream()
                .map(book -> {
                    var author = authorDAO.findByBookIsbn(book.getIsbn());
                    book.setAuthor(author.orElse(null));

                    return book;
                })
                .toList();
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        var author = authorDAO.findByBookIsbn(isbn);
        var book = bookDAO.getByIsbn(isbn);

        return book.map(b -> {
            b.setAuthor(author.orElse(null));

            return b;
        });
    }

    @Override
    public Book saveBook(Book book, Long authorId) {
        book.setIsbn(UUID.randomUUID().toString());

        var inserted = bookDAO.insert(book, authorId);
        var author = authorDAO.findByBookIsbn(inserted.getIsbn());

        inserted.setAuthor(author.orElse(null));

        return inserted;
    }

    @Override
    public Book updateBook(Book book) {
        var updated = bookDAO.update(book);

        if (updated == null) {
            throw new BookOperationException(String.format("Book with isbn: %s could not be updated", book.getIsbn()));
        }

        if (updated.getAuthor() == null) {
            var author = authorDAO.findByBookIsbn(updated.getIsbn());
            updated.setAuthor(author.orElse(null));
        }

        return updated;
    }

    @Override
    public void deleteBookByIsbn(String isbn) {
        bookDAO.deleteByIsbn(isbn);
    }
}
