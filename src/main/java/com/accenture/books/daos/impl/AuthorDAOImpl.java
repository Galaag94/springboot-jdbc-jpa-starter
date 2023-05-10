package com.accenture.books.daos.impl;

import com.accenture.books.daos.AuthorDAO;
import com.accenture.books.domain.Author;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.Optional;

@Component
public class AuthorDAOImpl implements AuthorDAO {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Author> authorRowMapper = (ResultSet rs, int rowNum) -> new Author(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getInt("age")
    );

    public AuthorDAOImpl(@Qualifier("bookstoreJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Author> findByBookIsbn(String isbn) {
        var sql = """
                select
                	a.id,
                	a.name,
                	a.age
                from
                	authors a
                inner join books b on
                	b.author_id = a.id
                where
                	b.isbn = ?""";

        return jdbcTemplate.query(sql, authorRowMapper, isbn)
                .stream()
                .findFirst();
    }
}
