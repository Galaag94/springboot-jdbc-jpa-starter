package com.accenture.books.daos.impl;

import com.accenture.books.daos.BookDAO;
import com.accenture.books.domain.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAOImpl implements BookDAO {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Book> bookMapper = (ResultSet rs, int rowNum) -> new Book(
            rs.getString("isbn"),
            rs.getString("title")
    );

    public BookDAOImpl(@Qualifier("bookstoreJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> getAll() {
        var sql = "select isbn, title from books";

        return jdbcTemplate.query(sql, bookMapper);
    }

    @Override
    public Optional<Book> getByIsbn(String isbn) {
        var sql = "select isbn, title from books where isbn = ?";

        return jdbcTemplate.query(sql, bookMapper, isbn)
                .stream()
                .findFirst();
    }

    @Override
    public Book insert(Book book, Long authorId) {
        var sql = "insert into books(isbn, title, author_id) values(? , ?, ?)";

        var rows = jdbcTemplate.update(sql, book.getIsbn(), book.getTitle(), authorId);

        return rows > 0 ? book : null;
    }

    @Override
    public Book update(Book book) {
        var sql = "update books set title = ?, author_id = ? where isbn = ?";

        var rows = jdbcTemplate.update(sql, book.getTitle(), book.getAuthor().getId(), book.getIsbn());

        return rows > 0 ? book : null;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        var sql = "delete from books where isbn = ?";

        jdbcTemplate.update(sql, isbn);
    }
}
