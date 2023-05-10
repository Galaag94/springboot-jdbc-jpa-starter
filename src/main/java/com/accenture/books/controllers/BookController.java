package com.accenture.books.controllers;

import com.accenture.books.dtos.BookDTO;
import com.accenture.books.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{isbn}")
    public BookDTO getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @PostMapping
    public ResponseEntity<BookDTO> saveBook(
            @RequestBody BookDTO bookDTO,
            @RequestParam("author_id") Long authorId
    ) {
        var savedBook = bookService.addBook(bookDTO, authorId);

        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BookDTO> updateBook(
            @RequestBody BookDTO bookDTO,
            @PathVariable("isbn") String isbn
    ) {
        var updatedBook = bookService.updateBook(bookDTO);

        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{isbn}")
    public void deleteBookByIsbn(@PathVariable("isbn") String isbn) {
        bookService.deleteBookByIsbn(isbn);
    }
}
